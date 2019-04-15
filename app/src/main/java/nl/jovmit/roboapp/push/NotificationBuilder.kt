package nl.jovmit.roboapp.push

import nl.jovmit.roboapp.push.data.NotificationData
import nl.jovmit.roboapp.push.items.NotificationItem

class NotificationBuilder(
    private val systemNotificationMaker: SystemNotificationMaker
) {

    fun build(notificationItem: NotificationItem): NotificationData {
        val systemNotification = systemNotificationMaker.make(notificationItem)
        return NotificationData(notificationItem.notificationId, systemNotification)
    }
}
