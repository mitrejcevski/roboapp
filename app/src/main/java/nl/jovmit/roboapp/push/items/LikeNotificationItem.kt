package nl.jovmit.roboapp.push.items

data class LikeNotificationItem(
    override val notificationId: Int
) : NotificationItem {

    override fun channel(): Int {
        TODO("not implemented")
    }

    override fun title(): Int {
        TODO("not implemented")
    }

    override fun message(): Int {
        TODO("not implemented")
    }

    override fun icon(): Int {
        TODO("not implemented")
    }
}
