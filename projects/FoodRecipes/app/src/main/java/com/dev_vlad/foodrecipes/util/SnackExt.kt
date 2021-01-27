package com.dev_vlad.foodrecipes.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dev_vlad.foodrecipes.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
        msgResId: Int? = null,
        messageAsStr : String? = null,
        isErrorMsg: Boolean = true,
        actionMessage: Int? = null,
        actionToTake: ((View) -> Unit) = {}
) {

    val showForTime =
            if (actionMessage == null) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE
    val message = messageAsStr ?: if(msgResId != null) context.getString(msgResId) else "info"
    val snackBar = Snackbar.make(this, message , showForTime)
    val mainSnackBarTxt =
            snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    val actionTxt =
            snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)


    //set text color
    if (isErrorMsg) {
        //set background color
        snackBar.view.setBackgroundColor(
                ContextCompat.getColor(
                        this.context,
                        R.color.white
                )
        )
        mainSnackBarTxt.setTextColor(ContextCompat.getColor(this.context, R.color.red))
        actionTxt.setTextColor(ContextCompat.getColor(this.context, R.color.red))
    } else {
        //set background color
        snackBar.view.setBackgroundColor(
                ContextCompat.getColor(
                        this.context,
                        R.color.purple_700
                )
        )
        mainSnackBarTxt.setTextColor(ContextCompat.getColor(this.context, R.color.white))
        actionTxt.setTextColor(ContextCompat.getColor(this.context, R.color.white))
    }

    /*set the font
    ResourcesCompat.getFont(this.context, R.font.poppins)?.let {
        mainSnackBarTxt.typeface = it
        actionTxt.typeface = it
    }*/

    //set the size
    mainSnackBarTxt.textSize = 16.toFloat()
    actionTxt.textSize = 16.toFloat()

    //display snackBar
    if (actionMessage != null) {
        snackBar.setAction(context.getString(actionMessage)) {
            actionToTake(this)
        }.show()
    } else {
        snackBar.show()
    }
}