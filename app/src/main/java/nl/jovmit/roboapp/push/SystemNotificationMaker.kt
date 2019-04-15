package nl.jovmit.roboapp.push

import android.app.Notification
import android.content.Context
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import nl.jovmit.roboapp.push.items.NotificationItem

class SystemNotificationMaker(
    private val context: Context
) {

    fun make(notificationItem: NotificationItem): Notification {
        return with(notificationItem) {
            NotificationCompat.Builder(context, getString(channel()))
                .setContentTitle(getString(title()))
                .setContentText(getString(message()))
                .setSmallIcon(icon())
                .setDefaults(Notification.DEFAULT_ALL)
                .build()
        }
    }

    private fun getString(@StringRes resource: Int): String {
        return context.getString(resource)
    }
}
