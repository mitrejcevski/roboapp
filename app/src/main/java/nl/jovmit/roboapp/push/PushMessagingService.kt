package nl.jovmit.roboapp.push

import android.app.NotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessagingService : FirebaseMessagingService() {

    private lateinit var handler: NotificationHandler

    override fun onCreate() {
        super.onCreate()

        val resolver = RemoteMessageResolver()
        val systemNotificationMaker = SystemNotificationMaker(this)
        val builder = NotificationBuilder(systemNotificationMaker)
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notifier = Notifier(notificationManager)
        handler = NotificationHandler(resolver, builder, notifier)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.let { message ->
            handler.handle(message.data)
        }
    }
}