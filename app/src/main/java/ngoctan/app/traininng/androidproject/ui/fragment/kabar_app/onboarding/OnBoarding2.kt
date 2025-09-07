package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentOnboarding2KabarBinding

class OnBoarding2: Fragment() {
    private lateinit var binding: FragmentOnboarding2KabarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding2KabarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNativeAd()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_onboarding2_to_fragment_onboarding3)
        }
    }

    fun showNativeAd() {
        AdsManager.loadNativeAd(binding.adView.root.context)
    }
}