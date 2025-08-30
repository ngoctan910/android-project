package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import coil.load
import ngoctan.app.traininng.androidproject.ads.AdsManager
import ngoctan.app.traininng.androidproject.ui.common.DataListAdapter
import ngoctan.app.traininng.androidproject.ui.common.DataViewHolder
import ngoctan.domain.model.news.Results
import ngoctan.domain.model.news.ResultsType
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemNativeAdBinding
import ngoctan.traininng.androidproject.databinding.ItemNewsHomeBinding

class NewsAdapter: DataListAdapter<Results, ViewBinding>(
    diffCallBack = object: DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(
            oldItem: Results,
            newItem: Results
        ): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(
            oldItem: Results,
            newItem: Results
        ): Boolean {
            return false
        }

    }
) {
    var onItemClick: ((Results) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0 && position != 0) {
            ResultsType.NativeAd.type
        } else
            ResultsType.ResultItem.type
    }
    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            ResultsType.ResultItem.type -> {
                ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }

            ResultsType.NativeAd.type -> {
                ItemNativeAdBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            else -> ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        }
    }

    override fun bind(
        binding: ViewBinding,
        item: Results,
        holder: DataViewHolder<ViewBinding>
    ) {
        when (binding) {
            is ItemNewsHomeBinding -> {
                showData(binding, item)
            }

            is ItemNativeAdBinding -> {
                bindAds(binding, item)
            }
        }
    }

    private fun showData(binding: ItemNewsHomeBinding, results: Results) {
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

        binding.root.setOnClickListener {
            onItemClick?.invoke(results)
        }
    }

    private fun bindAds(binding: ItemNativeAdBinding, results: Results) {
        AdsManager.loadNativeAd(binding.root.context)
    }
}