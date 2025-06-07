package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var newsAdapter: HomeNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callAPIs()
        initObserves()
    }

    private fun initView() {
        newsAdapter = HomeNewsAdapter()
        binding.recycleView.adapter = newsAdapter
        newsAdapter.onItemClick = { result ->
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentDetailNews(result))
        }
    }

    private fun callAPIs() {
        viewModel.fetchNewsArticle()
    }

    private fun initObserves() {
        lifecycleScope.launch {
            viewModel.uiState.collect { newsState ->
                newsState.news?.results?.let { newsAdapter.submitList(it) }
            }
        }
    }
}