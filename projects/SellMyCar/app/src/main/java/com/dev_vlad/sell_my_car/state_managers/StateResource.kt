package com.dev_vlad.sell_my_car.state_managers
import android.view.View
import com.dev_vlad.sell_my_car.utils.TodoCallback


data class StateMessage(val response: Response)

data class Response(
  val message: String?,
  val uiComponentType: UIComponentType,
  val messageType: MessageType
)

sealed class UIComponentType{

  class Toast: UIComponentType()

  class Dialog: UIComponentType()

  class AreYouSureDialog(
    val callback: AreYouSureCallback
  ): UIComponentType()

  class SnackBar(
    val undoCallback: SnackBarUndoCallback? = null,
    val onDismissCallback: TodoCallback? = null
  ): UIComponentType()

  class None: UIComponentType()
}

sealed class MessageType{

  class Success: MessageType()

  class Error: MessageType()

  class Info: MessageType()

  class None: MessageType()
}


interface StateMessageCallback{

  fun removeMessageFromStack()
}


interface AreYouSureCallback {

  fun proceed()

  fun cancel()
}

interface SnackBarUndoCallback {

  fun undo()
}

class SnackBarUndoListener
constructor(
  private val snackBarUndoCallback: SnackBarUndoCallback?
): View.OnClickListener {

  override fun onClick(v: View?) {
    snackBarUndoCallback?.undo()
  }

}


interface DialogInputCaptureCallback {

  fun onTextCaptured(text: String)
}
