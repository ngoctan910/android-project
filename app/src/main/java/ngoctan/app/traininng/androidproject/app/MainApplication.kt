package ngoctan.app.traininng.androidproject.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ngoctan.app.traininng.androidproject.util.extension.Logger

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        Logger.init()
    }

    companion object {
        private var instance: MainApplication? = null

        fun getInstance(): MainApplication {
            if (instance != null) instance = MainApplication()
            return instance!!
        }
    }
}