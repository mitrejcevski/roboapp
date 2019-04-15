package nl.jovmit.roboapp.push.items

interface NotificationItem {

    val notificationId: Int

    fun channel(): Int

    fun title(): Int

    fun message(): Int

    fun icon(): Int
}
