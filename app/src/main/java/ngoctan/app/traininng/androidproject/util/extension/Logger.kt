package ngoctan.app.traininng.androidproject.util.extension

import ngoctan.traininng.androidproject.BuildConfig
import timber.log.Timber

object Logger {
    private const val TAG = "AndroidProject"

    fun d(s: String?, vararg objects: Any?) {
        Timber.tag(TAG).d(s, *objects)
    }

    fun init() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}