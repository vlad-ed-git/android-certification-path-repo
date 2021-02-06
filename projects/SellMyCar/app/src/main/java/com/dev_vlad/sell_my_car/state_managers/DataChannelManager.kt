package com.dev_vlad.sell_my_car.state_managers


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*


abstract class DataChannelManager<ViewState> {

  private var channelScope: CoroutineScope? = null
  private val stateEventManager: StateEventManager = StateEventManager()

  val messageStack = MessageStack()

  val shouldDisplayProgressBar = stateEventManager.shouldDisplayProgressBar

  fun setupChannel(){
    cancelJobs()
  }

  abstract fun handleNewData(data: ViewState)

  fun launchJob(
    stateEvent: StateEvent,
    jobFunction: Flow<DataState<ViewState>?>
  ){
    if(canExecuteNewStateEvent(stateEvent)){

      addStateEvent(stateEvent)
      jobFunction
        .onEach { dataState ->
          dataState?.let { dataState ->
            withContext(Main){
              dataState.data?.let { data ->
                handleNewData(data)
              }
              dataState.stateMessage?.let { stateMessage ->
                handleNewStateMessage(stateMessage)
              }
              dataState.stateEvent?.let { stateEvent ->
                removeStateEvent(stateEvent)
              }
            }
          }
        }
        .launchIn(getChannelScope())
    }
  }

  private fun canExecuteNewStateEvent(stateEvent: StateEvent): Boolean{
    // If a job is already active, do not allow duplication
    if(isJobAlreadyActive(stateEvent)){
      return false
    }
    // if a dialog is showing, do not allow new StateEvents
    if(!isMessageStackEmpty()){
      return false
    }
    return true
  }

  fun isMessageStackEmpty(): Boolean {
    return messageStack.isStackEmpty()
  }

  private fun handleNewStateMessage(stateMessage: StateMessage){
    appendStateMessage(stateMessage)
  }

  private fun appendStateMessage(stateMessage: StateMessage) {
    messageStack.add(stateMessage)
  }

  fun clearStateMessage(index: Int = 0){
    messageStack.removeAt(index)
  }

  fun clearAllStateMessages() = messageStack.clear()

  fun printStateMessages(){
    for(message in messageStack){
    }
  }

  // for debugging
  fun getActiveJobs() = stateEventManager.getActiveJobNames()

  fun clearActiveStateEventCounter()
          = stateEventManager.clearActiveStateEventCounter()

  fun addStateEvent(stateEvent: StateEvent)
          = stateEventManager.addStateEvent(stateEvent)

  fun removeStateEvent(stateEvent: StateEvent?)
          = stateEventManager.removeStateEvent(stateEvent)

  private fun isStateEventActive(stateEvent: StateEvent)
          = stateEventManager.isStateEventActive(stateEvent)

  fun isJobAlreadyActive(stateEvent: StateEvent): Boolean {
    return isStateEventActive(stateEvent)
  }

  fun getChannelScope(): CoroutineScope {
    return channelScope?: setupNewChannelScope(CoroutineScope(IO))
  }

  private fun setupNewChannelScope(coroutineScope: CoroutineScope): CoroutineScope{
    channelScope = coroutineScope
    return channelScope as CoroutineScope
  }

  fun cancelJobs(){
    if(channelScope != null){
      if(channelScope?.isActive == true){
        channelScope?.cancel()
      }
      channelScope = null
    }
    clearActiveStateEventCounter()
  }

}

