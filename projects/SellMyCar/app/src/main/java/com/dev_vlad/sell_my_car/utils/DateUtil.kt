package com.dev_vlad.sell_my_car.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateUtil{


     //input string: YYYY-MM-DD HH:mm:ss
    //output string: YYYY-MM-DD
     fun removeTimeFromDateString(dateAsString: String) : String{
         return dateAsString.substring(0, dateAsString.indexOf(" "))
     }


    fun fireStoreTimestampToString(timestamp: Timestamp):String{
        val dateFormat: SimpleDateFormat = SimpleDateFormat("y-MM-DD HH:mm:ss", Locale.getDefault())

         return dateFormat.format(timestamp.toDate())
    }

    fun dateStringToFireStoreTimestamp(dateAsString: String):Timestamp{
        val dateFormat: SimpleDateFormat = SimpleDateFormat("y-MM-DD HH:mm:ss", Locale.getDefault())

        return Timestamp(dateFormat.parse(dateAsString)!!)
    }

    fun getCurrentTimestampAsStr(): String{
        val dateFormat: SimpleDateFormat = SimpleDateFormat("y-MM-DD HH:mm:ss", Locale.getDefault())

        return dateFormat.format(Date())
    }


}