package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentOnboarding1KabarBinding

class OnBoarding1: Fragment() {
    private lateinit var binding: FragmentOnboarding1KabarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding1KabarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {

            findNavController().navigate(R.id.action_fragment_onboarding1_to_fragment_onboarding2)
        }
    }
}