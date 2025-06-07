package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ngoctan.domain.model.news.Results
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemNewsHomeBinding

class HomeNewsAdapter: RecyclerView.Adapter<HomeNewsAdapter.ItemNewsViewHolder>() {
    private var newsList = listOf<Results>()
    var onItemClick: ((Results) -> Unit)? = null

    fun submitList(list: List<Results>) {
        newsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNewsViewHolder {
        return ItemNewsViewHolder(ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ItemNewsViewHolder, position: Int) {
        val results = newsList[position]
        holder.bind(results)
    }

    inner class ItemNewsViewHolder(private val binding: ItemNewsHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.shapeIvNews.load(results.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.loading)
                error(R.drawable.news)
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
}