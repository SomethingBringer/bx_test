package com.example.android.test.ui.fragments.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.test.databinding.ItemBinding
import com.example.android.test.models.Wrapper

class ItemsAdapter(
    val onItemRemove: (item: Int) -> Unit
) : ListAdapter<Wrapper<Int>, ItemsAdapter.ItemHolder>(diff) {

    var items: List<Wrapper<Int>> = listOf()

    inner class ItemHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Wrapper<Int>, position: Int) {
            binding.number.text = model.value.toString()
            binding.delete.setOnClickListener {
                onItemRemove.invoke(model.value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<Wrapper<Int>>() {
            override fun areItemsTheSame(oldItem: Wrapper<Int>, newItem: Wrapper<Int>): Boolean {
                return oldItem.value == newItem.value
            }

            override fun areContentsTheSame(oldItem: Wrapper<Int>, newItem: Wrapper<Int>): Boolean {
                return oldItem.value == newItem.value            }

        }
    }
}