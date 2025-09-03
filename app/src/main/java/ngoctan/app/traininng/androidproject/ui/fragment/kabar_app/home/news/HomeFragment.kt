package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.app.traininng.androidproject.notification.Notification
import ngoctan.app.traininng.androidproject.work.ArticleWorkManager
import ngoctan.traininng.androidproject.databinding.FragmentHomeBinding
import java.time.Duration
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callAPIs()
        initObserves()
        startWorker()
    }

    private fun initView() {
        newsAdapter = NewsAdapter()
        binding.recycleView.adapter = newsAdapter
        newsAdapter.onItemClick = { result ->
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentDetailNews(result))
            lifecycleScope.launch {
                Notification.createFirstNotification(requireContext(), result)
            }
        }
    }

    private fun callAPIs() {
        viewModel.fetchNewsArticle()
    }

    private fun initObserves() {
        lifecycleScope.launch {
            viewModel.uiState.collect { newsState ->
                newsState.resultList.let { newsAdapter.submitList(it) }
                binding.progressBar.isInvisible = !newsState.isLoading
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startWorker() {
        val now = ZonedDateTime.now()
        val today8 = now.withHour(8).withMinute(0).withSecond(0)
        val trigger = if (now < today8) today8 else today8.plusDays(1)
        val initialDelay = Duration.between(now, trigger).seconds

        val periodicRequest = PeriodicWorkRequestBuilder<ArticleWorkManager>(24, TimeUnit.HOURS)
            .setInitialDelay(initialDelay, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(requireContext()).enqueue(periodicRequest)
    }



}