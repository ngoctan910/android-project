package ngoctan.app.traininng.androidproject.ui.common

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class DataListAdapter<T, V: ViewBinding> (
    diffCallBack: DiffUtil.ItemCallback<T>
): ListAdapter<T, DataViewHolder<V>> (
    AsyncDifferConfig.Builder<T>(diffCallBack)
        .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder<V> {
        val binding = createBinding(parent, viewType)
        return DataViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): V

    override fun onBindViewHolder(holder: DataViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position), holder)
    }

    protected abstract fun bind(binding: V, item: T, holder: DataViewHolder<V>)
}