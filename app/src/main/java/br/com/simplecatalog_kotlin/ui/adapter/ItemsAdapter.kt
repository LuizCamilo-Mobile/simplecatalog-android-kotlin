package br.com.simplecatalog_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.simplecatalog_kotlin.databinding.ItemRowBinding
import br.com.simplecatalog_kotlin.domain.model.Item

class ItemsAdapter(
    private val onItemClick: (Item) -> Unit
) : ListAdapter<Item, ItemsAdapter.VH>(ItemDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(
        private val binding: ItemRowBinding,
        private val onItemClick: (Item) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.title.text = item.title
            binding.subtitle.text = item.subtitle

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }
}

