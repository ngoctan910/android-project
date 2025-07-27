package ngoctan.app.traininng.androidproject.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import ngoctan.app.traininng.androidproject.ui.activity.MainActivity
import ngoctan.domain.model.news.Results
import ngoctan.traininng.androidproject.R

@SuppressLint("StaticFieldLeak")
object Notification {
    const val CHANNEL_ID = "News app"
    const val FIRST_NOTIFICATION_ID = 1

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context?.getString(R.string.channel_name)
            val descriptionText = context?.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    fun createFirstNotification(context: Context, article: Results) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingInt: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builderNotification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(article.articleId)
            .setContentText(article.title)
            .setSmallIcon(R.drawable.notification)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingInt)

        with (NotificationManagerCompat.from(context)) {
            if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(article.type, builderNotification.build())
        }
    }
}