package nl.jovmit.roboapp.push

import android.app.Notification
import android.app.NotificationManager
import com.nhaarman.mockitokotlin2.verify
import nl.jovmit.roboapp.push.data.NotificationData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NotifierShould {

    @Mock
    private lateinit var notificationManager: NotificationManager

    private lateinit var notifier: Notifier

    @Before
    fun setUp() {
        notifier = Notifier(notificationManager)
    }

    @Test
    fun deliverNotificationIntoNotificationManager() {
        val id = 123
        val systemNotification = Notification()

        notifier.notify(NotificationData(id, systemNotification))

        verify(notificationManager).notify(id, systemNotification)
    }
}