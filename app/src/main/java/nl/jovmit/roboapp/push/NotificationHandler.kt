package nl.jovmit.roboapp.push

class NotificationHandler(
    private val remoteMessageResolver: RemoteMessageResolver,
    private val notificationBuilder: NotificationBuilder,
    private val notifier: Notifier
) {

    fun handle(remoteMessage: Map<String, String>) {
        val notificationItem = remoteMessageResolver.resolve(remoteMessage)
        val notificationData = notificationBuilder.build(notificationItem)
        notifier.notify(notificationData)
    }
}
