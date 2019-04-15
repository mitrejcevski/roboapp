package nl.jovmit.roboapp.push.items

import nl.jovmit.roboapp.R

data class DefaultNotificationItem(
    override val notificationId: Int
) : NotificationItem {

    override fun channel(): Int = R.string.app_name

    override fun title(): Int = R.string.app_name

    override fun message(): Int = R.string.default_notification_message

    override fun icon(): Int = R.mipmap.ic_launcher
}
