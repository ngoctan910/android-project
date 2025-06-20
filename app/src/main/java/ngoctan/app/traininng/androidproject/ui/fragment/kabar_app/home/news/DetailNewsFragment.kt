package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import ngoctan.app.traininng.androidproject.ads.InterAdManager
import ngoctan.app.traininng.androidproject.util.extension.Constant
import ngoctan.domain.model.news.Results
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentDetailNewsBinding

class DetailNewsFragment: Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUrl()
        loadAd()
        onBackPressed()

        val adLoader = AdLoader.Builder(requireContext(), requireContext().getString(R.string.test_native_ad)
        )
            .forNativeAd { nativeAd ->

            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {

                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .withAdListener(object : AdListener() {

            })
            .build()

        adLoader.loadAds(AdRequest.Builder().build(), 3)
    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        binding.layoutBanner.adView.loadAd(adRequest)
    }

    private fun loadUrl() {
        val result = arguments?.getParcelable<Results>(Constant.RESULT_KEY)
        binding.webView.apply {
            result?.link?.let { loadUrl(it) }
            webViewClient = WebViewClient()
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack()
            InterAdManager.getInstance().showInterstitial(requireActivity())
        }
    }

}