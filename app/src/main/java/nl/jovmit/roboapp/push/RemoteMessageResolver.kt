package nl.jovmit.roboapp.push

import nl.jovmit.roboapp.push.items.DefaultNotificationItem
import nl.jovmit.roboapp.push.items.LikeNotificationItem
import nl.jovmit.roboapp.push.items.NotificationItem

class RemoteMessageResolver {

    private companion object {
        private const val EVENT_TYPE = "event_type"
        private const val DEFAULT = "default"
        private const val LIKE = "like"

    }

    fun resolve(remoteMessage: Map<String, String>): NotificationItem {
        val eventType = remoteMessage[EVENT_TYPE]
        return when (eventType) {
            LIKE -> LikeNotificationItem(eventType.hashCode())
            else -> DefaultNotificationItem(DEFAULT.hashCode())
        }
    }
}
