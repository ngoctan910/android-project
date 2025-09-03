package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.select_country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ngoctan.domain.model.select_country.SelectCountry
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemCountryBinding

class SelectCountryAdapter: RecyclerView.Adapter<SelectCountryAdapter.ItemSelectCountryViewHolder>() {
    private var listCountry = listOf<SelectCountry>()
    private var selectedPosition = -1

    fun submitList(list: List<SelectCountry>) {
        listCountry = list
        notifyDataSetChanged()
    }

    inner class ItemSelectCountryViewHolder(val binding: ItemCountryBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(selectCountry: SelectCountry) {
                binding.flag.load(selectCountry.flags?.png) {
                    crossfade(true)
                    placeholder(R.drawable.loading)
                }

                binding.country.text = selectCountry.name?.common
                itemView.setOnClickListener {
                    val previousPosition = selectedPosition
                    selectedPosition = adapterPosition
                    notifyItemChanged(previousPosition)
                    notifyItemChanged(selectedPosition)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSelectCountryViewHolder {
        return ItemSelectCountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int = listCountry.size


    override fun onBindViewHolder(holder: ItemSelectCountryViewHolder, position: Int) {
        holder.bind(listCountry[position])
        holder.itemView.setBackgroundResource(if (position == selectedPosition) R.drawable.bg_blue_rounded else R.color.white)
    }
}
