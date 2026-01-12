package br.com.simplecatalog_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.simplecatalog_kotlin.databinding.ItemRowBinding
import br.com.simplecatalog_kotlin.domain.model.Item

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.VH>() {

    private val data = mutableListOf<Item>()

    fun submitList(newList: List<Item>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged() // suficiente para o exercício 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class VH(
        private val binding: ItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.title.text = item.title
            binding.subtitle.text = item.subtitle
        }
    }
}

