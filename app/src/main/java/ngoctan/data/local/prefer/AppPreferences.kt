package ngoctan.data.local.prefer

import android.content.Context
import ngoctan.app.traininng.androidproject.app.MainApplication
import java.lang.ref.WeakReference
import javax.inject.Singleton

@Singleton
class AppPreferences(
    private val context: Context
): Preferences(context) {
    var isFirstOpen by booleanRef("isFirstOpen", true)
    companion object {
        private var instance: AppPreferences? = null

        fun init(context: Context) {
            instance = AppPreferences(context.applicationContext)
        }

        fun getInstance(): AppPreferences {
            return instance ?: run {
                AppPreferences(WeakReference(MainApplication.getInstance()).get() ?: throw IllegalStateException("Application context is null"))
            }
        }
    }
}