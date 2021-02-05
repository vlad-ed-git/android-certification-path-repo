package com.dev_vlad.sell_my_car.state_managers

interface StateEvent {

  fun errorInfo(): String

  fun eventName(): String

  fun shouldDisplayProgressBar(): Boolean
}