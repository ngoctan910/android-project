package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ngoctan.app.traininng.androidproject.util.extension.Constant
import ngoctan.domain.model.news.Results
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
    }

    private fun loadUrl() {
        val result = arguments?.getParcelable<Results>(Constant.RESULT_KEY)
        binding.webView.apply {
            result?.link?.let { loadUrl(it) }
            webViewClient = WebViewClient()
        }
    }
}