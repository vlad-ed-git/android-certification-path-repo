package com.dev_vlad.sell_my_car.data_sources.remote

import com.dev_vlad.sell_my_car.state_managers.*



abstract class NetworkResponseHandler <ViewState, Data>(
        private val response: NetworkResult<Data?>,
        private val stateEvent: StateEvent?
){

    suspend fun getResult(): DataState<ViewState>? {

        return when(response){

            is NetworkResult.GenericError -> {
                DataState.error(
                        response = Response(
                                message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage.toString()}",
                                uiComponentType = UIComponentType.Dialog(),
                                messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                )
            }

            is NetworkResult.NetworkError -> {
                DataState.error(
                        response = Response(
                                message = "${stateEvent?.errorInfo()}\n\nReason: there was a network error!",
                                uiComponentType = UIComponentType.Dialog(),
                                messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                )
            }

            is NetworkResult.Success -> {
                if(response.value == null){
                    DataState.error(
                            response = Response(
                                    message = "${stateEvent?.errorInfo()}\n\nReason: the network data returned null",
                                    uiComponentType = UIComponentType.Dialog(),
                                    messageType = MessageType.Error()
                            ),
                            stateEvent = stateEvent
                    )
                }
                else{
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}