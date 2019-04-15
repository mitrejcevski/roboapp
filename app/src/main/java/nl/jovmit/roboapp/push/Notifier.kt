package nl.jovmit.roboapp.push

import android.app.NotificationManager
import nl.jovmit.roboapp.push.data.NotificationData

class Notifier(
    private val notificationManager: NotificationManager
) {

    fun notify(notificationData: NotificationData) {
        with(notificationData) {
            notificationManager.notify(notificationId, systemNotification)
        }
    }
}
