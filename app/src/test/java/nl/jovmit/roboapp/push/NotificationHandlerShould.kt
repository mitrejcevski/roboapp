package nl.jovmit.roboapp.push

import android.app.Notification
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import nl.jovmit.roboapp.push.data.NotificationData
import nl.jovmit.roboapp.push.items.DefaultNotificationItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotificationHandlerShould {

    @Mock
    private lateinit var remoteMessageResolver: RemoteMessageResolver
    @Mock
    private lateinit var notificationBuilder: NotificationBuilder
    @Mock
    private lateinit var notifier: Notifier

    private val remoteMessage = emptyMap<String, String>()
    private val notificationItem = DefaultNotificationItem(123)

    private lateinit var notificationHandler: NotificationHandler

    @Before
    fun setUp() {
        notificationHandler = NotificationHandler(remoteMessageResolver, notificationBuilder, notifier)
        whenever(remoteMessageResolver.resolve(remoteMessage)).thenReturn(notificationItem)
    }

    @Test
    fun resolveRemoteMessageIntoNotificationItem() {
        notificationHandler.handle(remoteMessage)

        verify(remoteMessageResolver).resolve(remoteMessage)
    }

    @Test
    fun buildNotificationDataFromNotificationItem() {
        notificationHandler.handle(remoteMessage)

        verify(notificationBuilder).build(notificationItem)
    }

    @Test
    fun deliverNotificationDataToNotifier() {
        val notificationId = 123
        val systemNotification = Notification()
        val notificationData = NotificationData(notificationId, systemNotification)
        given(notificationBuilder.build(notificationItem)).willReturn(notificationData)

        notificationHandler.handle(remoteMessage)

        verify(notifier).notify(notificationData)
    }
}