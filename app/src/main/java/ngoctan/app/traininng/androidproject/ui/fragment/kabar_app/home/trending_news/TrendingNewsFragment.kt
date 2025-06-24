package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.trending_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.databinding.FragmentTrendingNewsBinding

@AndroidEntryPoint
class TrendingNewsFragment: Fragment() {
    private lateinit var binding: FragmentTrendingNewsBinding
    private lateinit var newsAdapter: TrendingNewsAdapter
    private val viewModel by viewModels<TrendingNewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApi()
        initView()
        initObserver()
    }

    private fun callApi() {
        viewModel.fetchTrendingNewsArticles()
    }

    private fun initView() {
        newsAdapter = TrendingNewsAdapter()
        binding.recyclerView.adapter = newsAdapter
        newsAdapter.onItemClick = { results ->
            findNavController().navigate(TrendingNewsFragmentDirections.actionFragmentTrendingNewsToFragmentDetailNews(results))
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.uiState.collect { trendingNewsState ->
                binding.progressBar.isInvisible = !trendingNewsState.isLoading

                trendingNewsState.newsList?.let {
                    newsAdapter.submitList(it)
                }
            }
        }
    }
}