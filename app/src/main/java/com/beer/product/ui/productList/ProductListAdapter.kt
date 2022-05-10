package com.beer.product.ui.productList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.beer.product.domain.ProductListDomainModel
import com.example.product.databinding.ListProductBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ProductListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<ProductListDomainModel, ProductListAdapter.ViewHolder>(ProductListDiffCallback()) {

    class ViewHolder(val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductListDomainModel, clickListener: ClickListener) {
            binding.beerName.text = item.name
            binding.tagline.text = item.tagline
            binding.beerImage.load(item.imageUrl)
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListProductBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beerList = getItem(position)
        holder.bind(beerList, clickListener)
    }

    class ProductListDiffCallback : DiffUtil.ItemCallback<ProductListDomainModel>() {

        override fun areItemsTheSame(
            oldItem: ProductListDomainModel,
            newItem: ProductListDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ProductListDomainModel,
            newItem: ProductListDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class ClickListener @Inject constructor() {

    var onItemClick: ((ProductListDomainModel) -> Unit)? = null

    fun onClick(data: ProductListDomainModel) {
        onItemClick?.invoke(data)
    }
}

