package com.dev_vlad.foodrecipes.util.network_resources

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dev_vlad.foodrecipes.AppExecutors

// DataFromCache: Type for the Resource data.
// DataFromNetwork: Type for the API response.
abstract class NetworkBoundResource<DataFromCache, DataFromNetwork>
@MainThread constructor(private val executors : AppExecutors) {


    private val observableResults = MediatorLiveData<Resource<DataFromCache>>()


    init {
        //set status to loading
        observableResults.value = Resource.loading(null)

        //observe liveData from cache
        @Suppress("LeakingThis")
        val dbSource : LiveData<DataFromCache> = loadFromDb()
        observableResults.addSource(dbSource) { data ->

            //remove the source so it stops listening to it
            observableResults.removeSource(dbSource)

            if (shouldFetch(data)) {
                //get data from the network
                    //i.e. refresh the cache
                fetchFromNetwork(dbSource)
            } else {
                //re add source whose new data will be added
                observableResults.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<DataFromCache>) {
        if (observableResults.value != newValue) {
            observableResults.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<DataFromCache>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly
        observableResults.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        observableResults.addSource(apiResponse) { response ->
            observableResults.removeSource(apiResponse)
            observableResults.removeSource(dbSource)
            when (response) {
                is GenericApiResponse.Companion.GenericApiSuccessResponse -> {
                    AppExecutors.getDiskIO().execute {
                        saveCallResult(processResponse(response))
                        AppExecutors.getMainThreadExec().execute {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            observableResults.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is GenericApiResponse.Companion.GenericApiEmptyResponse -> {
                    AppExecutors.getMainThreadExec().execute {
                        // reload from disk whatever we had
                        observableResults.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is GenericApiResponse.Companion.GenericApiErrorResponse -> {
                    onFetchFailed()
                    observableResults.addSource(dbSource) { newData ->
                        val errMsg = response.errorMessage?:"Error fetching from network"
                        setValue(Resource.error(errMsg, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected open fun processResponse(response: GenericApiResponse.Companion.GenericApiSuccessResponse<DataFromNetwork>) = response.body

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract fun saveCallResult(item: DataFromNetwork)

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract fun shouldFetch(data: DataFromCache?): Boolean

    // Called to get the cached data from the database.
    @MainThread
    protected abstract fun loadFromDb(): LiveData<DataFromCache>

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): LiveData<GenericApiResponse<DataFromNetwork>>


    fun resultsAsLiveData() : LiveData<Resource<DataFromCache>> = observableResults
}
