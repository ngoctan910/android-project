package ngoctan.app.traininng.androidproject.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.traininng.androidproject.R

object AdsManager {

    var nativeAd: NativeAd? = null
    private var mInterstitialAd: InterstitialAd? = null
    const val MAX_LOAD_AD_FAILED = 2
    var nativeAdLoadCount = 0
    var interAdLoadCount = 0

    fun loadNativeAd(context: Context, onAdLoaded: ((NativeAd) -> Unit)? = null) {
        CoroutineScope(Dispatchers.IO).launch {
            val adLoader = AdLoader.Builder(context, context.getString(R.string.native_ad))
                .forNativeAd { ad: NativeAd ->
                    nativeAd = ad
                    onAdLoaded?.invoke(ad)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        Logger.d("Native ad loaded successfully")
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        super.onAdFailedToLoad(error)
                        Logger.d("Native ad load failed: $error")
                        nativeAdLoadCount++
                        if (nativeAdLoadCount < MAX_LOAD_AD_FAILED) {
                            loadNativeAd(context)
                        }
                    }
                })
                .withNativeAdOptions(NativeAdOptions.Builder()
                    .setVideoOptions(VideoOptions.Builder()
                        .setStartMuted(true)
                        .setClickToExpandRequested(true)
                        .build()
                    )
                    .build()
                )
                .build()

            adLoader.loadAd(AdRequest.Builder().build())
        }

    }

    fun loadInterstitialAd(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, context.getString(R.string.inter_ad), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                Logger.d("Interstitial ad loaded successfully")
                mInterstitialAd = interstitialAd
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                super.onAdFailedToLoad(adError)
                Logger.d("Interstitial ad failed to load: ${adError.message}")
                interAdLoadCount++
                if (interAdLoadCount < MAX_LOAD_AD_FAILED) {
                    loadInterstitialAd(context)
                }
            }
        })
    }

    fun showInterstitialAd(activity: Activity) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                super.onAdClicked()
                Logger.d("Interstitial Ad was clicked")
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                Logger.d("Interstitial Ad dismissed fullscreen content")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                super.onAdFailedToShowFullScreenContent(adError)
                Logger.d("Interstitial Ad failed to show fullscreen content: ${adError.message}")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                Logger.d("Interstitial Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                Logger.d("Interstitial Ad showed fullscreen content")
                loadInterstitialAd(activity)
            }
        }

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(activity)
        } else {
            Logger.d("The interstitial ad wasn't ready yet.")
        }
    }
}