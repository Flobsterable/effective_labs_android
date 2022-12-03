package ru.flobsterable.effectiveLabs.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.flobsterable.effectiveLabs.MainActivity
import ru.flobsterable.effectiveLabs.R

private const val NOTIFICATION_ID = 0
private const val REQUEST_CODE = 0

class AppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {

        with(this) {
            createChannel(
                getString(R.string.notification_channel_id),
                getString(R.string.notification_channel_name),
                getString(R.string.notification_channel_description)
            )
        }

        val id = remoteMessage.data["id"]
        val uri = "https://www.flobsterable.ru/$id".toUri()
        val notificationText = remoteMessage.notification?.body
        val notificationTitle = remoteMessage.notification?.title

        val deepLinkIntent = Intent(
            Intent.ACTION_VIEW,
            uri,
            this,
            MainActivity::class.java
        )
        val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(deepLinkIntent)
            getPendingIntent(REQUEST_CODE, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT) }

        val builder = NotificationCompat.Builder(this, this.getString(R.string.notification_channel_id))
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(deepLinkPendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build()) }
    }

    private fun createChannel(
        channelId: String,
        channelName: String,
        channelDescription: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { setShowBadge(false) }

            with(notificationChannel){
                enableLights(true)
                lightColor = Color.Red.toArgb()
                enableVibration(true)
                description = channelDescription
            }

            with(NotificationManagerCompat.from(this)){
                createNotificationChannel(notificationChannel) }
        }
    }
}
