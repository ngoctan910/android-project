package ngoctan.app.traininng.androidproject.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import dagger.hilt.android.AndroidEntryPoint
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.app.traininng.androidproject.notification.Notification
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestNotificationLauncher =
        registerForActivityResult(RequestPermission()) { isGranted: Boolean ->

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
        initMobileAds()
        loadAds()
        checkRequestNotificationPermission()
        Notification.createNotificationChannel(this)
    }

    private fun initMobileAds() {
        Thread {
            MobileAds.initialize(this) { initializationStatus: InitializationStatus? -> }
        }.start()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home,
                R.id.fragment_trending_news -> binding.bottomNavigation.visibility = View.VISIBLE

                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

    private fun loadAds() {
        AdsManager.loadInterstitialAd(this)
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

    private fun checkRequestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}