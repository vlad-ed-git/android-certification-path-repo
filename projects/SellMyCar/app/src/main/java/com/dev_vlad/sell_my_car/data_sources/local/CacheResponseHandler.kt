package com.dev_vlad.sell_my_car.data_sources.local

import com.dev_vlad.sell_my_car.state_managers.*


abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>,
    private val stateEvent: StateEvent?
) {
    suspend fun getResult() : DataState<ViewState>?{
        return when(response){
            is CacheResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()} " + "Reason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                if (response.value == null){
                    DataState.error(
                        response = Response(
                            message = "${stateEvent?.errorInfo()} " + "Reason: cache error data is null",
                            uiComponentType = UIComponentType.Dialog(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                }
                else {
                    handleSuccess(resultObj = response.value)
                }
            }
        }
    }


    abstract fun handleSuccess(resultObj : Data) : DataState<ViewState>
}