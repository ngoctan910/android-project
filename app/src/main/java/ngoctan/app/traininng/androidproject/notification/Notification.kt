package ngoctan.app.traininng.androidproject.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import ngoctan.app.traininng.androidproject.ui.activity.MainActivity
import ngoctan.domain.model.news.Results
import ngoctan.traininng.androidproject.R
import coil.request.SuccessResult

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

     suspend fun createFirstNotification(context: Context, results: Results) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingInt: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(results.imageUrl) // có thể là URL, Uri... Coil sẽ hỗ trợ
            .allowHardware(false) // cần để chuyển sang Bitmap
            .build()

        val result = imageLoader.execute(request)
        val bitmap = (result as? SuccessResult)?.drawable?.toBitmap()

        if (bitmap != null) {
            val remoteView = RemoteViews(context.packageName, R.layout.item_notification).apply {
                setImageViewBitmap(R.id.iconNotification, bitmap)
                setTextViewText(R.id.titleNews, results.title)
            }

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setCustomContentView(remoteView)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null as Icon?)
                        .setBigContentTitle(results.title)
                )
                .setAutoCancel(true)
                .setContentIntent(pendingInt)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            NotificationManagerCompat.from(context).notify(FIRST_NOTIFICATION_ID, builder.build())
        }
    }
}