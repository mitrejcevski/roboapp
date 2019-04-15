package nl.jovmit.roboapp.push

import nl.jovmit.roboapp.push.items.DefaultNotificationItem
import nl.jovmit.roboapp.push.items.LikeNotificationItem
import org.junit.Assert.*
import org.junit.Test

class RemoteMessageResolverShould {

    private val remoteMessageResolver = RemoteMessageResolver()

    @Test
    fun returnDefaultNotificationItemForEmptyRemoteMessage() {
        val emptyRemoteMessage = emptyMap<String, String>()

        val result = remoteMessageResolver.resolve(emptyRemoteMessage)

        val notificationId = "default".hashCode()
        val defaultNotificationItem = DefaultNotificationItem(notificationId)
        assertEquals(defaultNotificationItem, result)
    }

    @Test
    fun returnLikeNotificationItemForLikeRemoteMessage() {
        val likeRemoteMessage = mapOf("event_type" to "like")

        val result = remoteMessageResolver.resolve(likeRemoteMessage)

        val likeNotificationId = "like".hashCode()
        val likeNotificationItem = LikeNotificationItem(likeNotificationId)
        assertEquals(likeNotificationItem, result)
    }
}