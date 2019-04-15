package nl.jovmit.roboapp.push

import android.app.Notification
import com.nhaarman.mockitokotlin2.given
import nl.jovmit.roboapp.push.data.NotificationData
import nl.jovmit.roboapp.push.items.DefaultNotificationItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotificationBuilderShould {

    @Mock
    private lateinit var systemNotificationMaker: SystemNotificationMaker

    private lateinit var notificationBuilder: NotificationBuilder

    @Before
    fun setUp() {
        notificationBuilder = NotificationBuilder(systemNotificationMaker)
    }

    @Test
    fun buildNotificationDataFromNotificationItem() {
        val notificationId = 123
        val systemNotification = Notification()
        val notificationItem = DefaultNotificationItem(notificationId)
        given(systemNotificationMaker.make(notificationItem)).willReturn(systemNotification)

        val result = notificationBuilder.build(notificationItem)

        val notificationData = NotificationData(notificationId, systemNotification)
        assertEquals(notificationData, result)
    }
}