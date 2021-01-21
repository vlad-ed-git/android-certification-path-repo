package com.dev_vlad.customtoast

import android.app.Activity
import android.content.Context
import android.provider.Settings.Global.getString
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

object CustomToast {
    fun showToast(context: Context,  message : String, image_res : Int) {
        val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val container :ViewGroup? = (context as Activity).findViewById(R.id.custom_toast_container)
        val layout: View = inflater.inflate(R.layout.custom_toast_layout, container)
        val text: TextView = layout.findViewById(R.id.text)
        text.text = message
        val image: ImageView = layout.findViewById(R.id.image)
        image.setImageResource(image_res)
        with(Toast(context)) {
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }
}