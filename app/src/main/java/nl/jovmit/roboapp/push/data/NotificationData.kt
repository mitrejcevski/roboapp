package nl.jovmit.roboapp.push.data

import android.app.Notification

data class NotificationData(
    val notificationId: Int,
    val systemNotification: Notification
)
