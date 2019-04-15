package nl.jovmit.roboapp.push

import android.app.Notification
import android.app.NotificationManager
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotificationFeature {

    @Mock
    private lateinit var notificationManager: NotificationManager
    @Mock
    private lateinit var systemNotificationMaker: SystemNotificationMaker

    private lateinit var notificationHandler: NotificationHandler

    @Before
    fun setUp() {
        val remoteMessageResolver = RemoteMessageResolver()
        val notificationBuilder = NotificationBuilder(systemNotificationMaker)
        val notifier = Notifier(notificationManager)
        notificationHandler = NotificationHandler(remoteMessageResolver, notificationBuilder, notifier)
    }

    @Test
    fun shouldDisplayNotification() {
        val notificationId = "default".hashCode()
        val systemNotification = Notification()
        given(systemNotificationMaker.make(any())).willReturn(systemNotification)

        val remoteMessage = emptyMap<String, String>()
        notificationHandler.handle(remoteMessage)

        verify(notificationManager).notify(notificationId, systemNotification)
    }
}