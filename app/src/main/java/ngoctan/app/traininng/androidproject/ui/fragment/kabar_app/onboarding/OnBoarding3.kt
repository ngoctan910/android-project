package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.data.local.prefer.AppPreferences
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentOnboarding3KabarBinding

class OnBoarding3: Fragment() {
    private lateinit var binding: FragmentOnboarding3KabarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding3KabarBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNativeAd()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.started.setOnClickListener {
            onboardingFinished()
        }
    }

    fun showNativeAd() {
        AdsManager.loadNativeAd(binding.adView.root.context)
    }

    private fun onboardingFinished() {
        AppPreferences.getInstance().isFirstOpen = false
        findNavController().navigate(R.id.action_fragment_onboarding3_to_fragment_home)
    }

}