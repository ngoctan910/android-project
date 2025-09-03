package ngoctan.app.traininng.androidproject.ui.fragment.mars

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ngoctan.domain.model.mars_photo.MarsPhoto
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.ItemMarsBinding

class MarsPhotoAdapter: RecyclerView.Adapter<MarsPhotoAdapter.ItemPhotoViewHolder>() {
    private var listPhotos = listOf<MarsPhoto>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MarsPhoto>) {
        listPhotos = list
        notifyDataSetChanged()
    }

    inner class ItemPhotoViewHolder(private var binding: ItemMarsBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: MarsPhoto) {
            binding.itemPhoto.load(photo.imgSrc) {
                crossfade(true)
                placeholder(R.drawable.loading)
            }

            binding.tvId.text = photo.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPhotoViewHolder {
        return ItemPhotoViewHolder(ItemMarsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPhotos.size

    override fun onBindViewHolder(holder: ItemPhotoViewHolder, position: Int) {
        holder.bind(listPhotos[position])
    }

}