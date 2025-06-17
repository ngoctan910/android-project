package ngoctan.app.traininng.androidproject.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import ngoctan.app.traininng.androidproject.app.MainApplication
import ngoctan.app.traininng.androidproject.app.MainApplication.Companion
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.traininng.androidproject.R
import java.lang.ref.WeakReference

class InterAdManager(private val context: Context) {
    private var mInterstitialAd: InterstitialAd? = null

    fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context,
            context.getString(R.string.test_inter_ad),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                    Logger.d("onAdLoaded: ${ad}")
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd
                    Logger.d("onAdFailedToLoad: ${adError.message}")
                }
            })
    }

    fun showInterstitial(activity: Activity) {
        mInterstitialAd?.show(activity)
    }

    companion object {
        private var instance = WeakReference<InterAdManager>(null)

        fun getInstance(): InterAdManager {
            if (instance.get() == null) instance = WeakReference(InterAdManager(MainApplication.getInstance()))
                return instance.get()!!

        }
    }
}