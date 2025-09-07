package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.spash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ngoctan.data.local.prefer.AppPreferences
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentSplashKabarBinding

class SplashKabarFragment: Fragment() {
    lateinit var binding: FragmentSplashKabarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashKabarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("isFirstOpen", AppPreferences.getInstance().isFirstOpen.toString())
            if (AppPreferences.getInstance().isFirstOpen)
                findNavController().navigate(R.id.action_fragment_splash_to_fragment_onboarding1)
            else
                findNavController().navigate(R.id.action_fragment_splash_to_fragment_home)

        }, 3000)
    }
}