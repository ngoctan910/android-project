package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.trending_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.domain.model.news.Results
import ngoctan.domain.model.news.ResultsType
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemNativeAdBinding
import ngoctan.traininng.androidproject.databinding.ItemTrendingNewsBinding


class TrendingNewsAdapter: RecyclerView.Adapter<ViewHolder>() {
    private var newsList = listOf<Results>()
    var onItemClick: ((Results) -> Unit)? = null

    fun submitList(list: List<Results>) {
        newsList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0 && position != 0) ResultsType.NativeAd.type else ResultsType.ResultItem.type
    }


    inner class ItemTrendingNewsViewHoder(private val binding: ItemTrendingNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.ship.load(results.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.loading)
                error(R.drawable.error)
            }

            binding.apply {
                category.text = results.category.toString()
                title.text = results.title.toString()
                country.text = results.country.toString()
                time.text = results.pubDate.toString()
                layout.setOnClickListener { onItemClick }
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(results)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ResultsType.ResultItem.type -> {
                val binding = (ItemTrendingNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                ItemTrendingNewsViewHoder(binding)
            }

            ResultsType.NativeAd.type -> {
                val binding = (ItemNativeAdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                NativeAdViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")

        }
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (newsList.isNotEmpty()) {
            val results = newsList[position]

            when (holder.itemViewType) {
                ResultsType.ResultItem.type -> {
                    (holder as ItemTrendingNewsViewHoder).bind(results)
                }

                ResultsType.NativeAd.type -> {
                    (holder as NativeAdViewHolder).loadNativeAd()
                }
            }
        }
    }

    class NativeAdViewHolder(private val binding: ItemNativeAdBinding): ViewHolder(binding.root) {
        fun loadNativeAd() {
            AdsManager.loadNativeAd(itemView.context)
        }
    }
}