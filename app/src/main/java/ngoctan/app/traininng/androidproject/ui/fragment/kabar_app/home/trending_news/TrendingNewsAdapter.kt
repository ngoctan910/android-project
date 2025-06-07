package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.trending_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ngoctan.domain.model.news.Results
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemTrendingNewsBinding


class TrendingNewsAdapter: RecyclerView.Adapter<TrendingNewsAdapter.ItemTrendingNewsViewModel>() {
    private var newsList = listOf<Results>()
    var onItemClick: ((Results) -> Unit)? = null

    fun submitList(list: List<Results>) {
        newsList = list
        notifyDataSetChanged()
    }

    inner class ItemTrendingNewsViewModel(private val binding: ItemTrendingNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.ship.load(results.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.loading)
                error(R.color.black)
            }

            binding.apply {
                category.text = results.category.toString()
                title.text = results.title
                country.text = results.category.toString()
                title.text = results.pubDate
                layout.setOnClickListener { onItemClick }
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(results)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTrendingNewsViewModel {
        return ItemTrendingNewsViewModel(ItemTrendingNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ItemTrendingNewsViewModel, position: Int) {
        val results = newsList[position]
        holder.bind(results)
    }
}