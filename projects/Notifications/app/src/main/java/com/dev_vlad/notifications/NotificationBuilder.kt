package com.dev_vlad.notifications

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationBuilder {
    const val DEFAULT_NOTIFICATION_CHANNEL_ID = "com.dev_vlad.default_notifications"
    fun displayNotification(
        context: Context,
        title: String,
        shortDescription: String,
        longerDescription: String,
        small_icon_res: Int,
        pendingIntent: PendingIntent,
        notificationId : Int
    ){
        val builder = NotificationCompat.Builder(context, DEFAULT_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(small_icon_res)
            .setContentTitle(title)
            .setContentText(shortDescription)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(longerDescription))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }
}