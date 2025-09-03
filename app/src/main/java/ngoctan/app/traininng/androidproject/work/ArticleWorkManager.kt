package ngoctan.app.traininng.androidproject.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ngoctan.app.traininng.androidproject.notification.Notification
import ngoctan.domain.model.news.Results

class ArticleWorkManager(
    private val context: Context,
    worker: WorkerParameters
): CoroutineWorker(context, worker) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            Notification.createFirstNotification(context, results = Results(title = "Work Manager"))
            Result.success()
        } catch (e: Throwable) {
            Result.failure()
        }

    }
}
