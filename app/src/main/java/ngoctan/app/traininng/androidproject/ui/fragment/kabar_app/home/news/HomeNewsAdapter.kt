package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.api.load
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.app.traininng.androidproject.util.extension.Logger
import ngoctan.domain.model.news.Results
import ngoctan.domain.model.news.ResultsType
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemNativeAdBinding
import ngoctan.traininng.androidproject.databinding.ItemNewsHomeBinding

class HomeNewsAdapter: RecyclerView.Adapter<ViewHolder>() {
    private var newsList = listOf<Results>()
    var onItemClick: ((Results) -> Unit)? = null

    fun submitList(list: List<Results>) {
        newsList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0 && position != 0) {
            ResultsType.NativeAd.type
        } else
            ResultsType.ResultItem.type

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ResultsType.ResultItem.type -> {
                val binding = (ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                ItemNewsViewHolder(binding)
            }

            ResultsType.NativeAd.type -> {
                val binding = (ItemNativeAdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                NativeAdViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (newsList.isNotEmpty()) {
            val results = newsList[position]

            when (holder.itemViewType) {
                ResultsType.ResultItem.type -> {
                    (holder as ItemNewsViewHolder).bind(results)
                }

                ResultsType.NativeAd.type -> {
                    (holder as NativeAdViewHolder).loadNativeAd()
                }
            }
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class ItemNewsViewHolder(private val binding: ItemNewsHomeBinding): ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.shapeIvNews.load(results.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.loading)
                error(R.drawable.error)
            }

            binding.apply {
                category.text = results.category?.first()
                name.text = results.title
                tvCountry.text = results.country?.first()
                tvTime.text = results.pubDate
                layout.setOnClickListener { onItemClick}
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(results)
            }
        }
    }

    class NativeAdViewHolder(private val binding: ItemNativeAdBinding): ViewHolder(binding.root) {
        fun loadNativeAd() {
            AdsManager.loadNativeAd(itemView.context)
        }
    }
}