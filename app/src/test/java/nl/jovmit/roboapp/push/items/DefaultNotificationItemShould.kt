package nl.jovmit.roboapp.push.items

import nl.jovmit.roboapp.R
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultNotificationItemShould {

    private val notification = DefaultNotificationItem(123)

    @Test
    fun returnCorrectChannel() {
        assertEquals(R.string.app_name, notification.channel())
    }

    @Test
    fun returnCorrectTitle() {
        assertEquals(R.string.app_name, notification.title())
    }

    @Test
    fun returnCorrectMessage() {
        assertEquals(R.string.default_notification_message, notification.message())
    }

    @Test
    fun returnCorrectIcon() {
        assertEquals(R.mipmap.ic_launcher, notification.icon())
    }
}