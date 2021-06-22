package com.example.biirr.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biirr.databinding.ListProductBinding
import com.example.biirr.model.Product
import com.example.biirr.ui.ProductDetailsActivity


class MainActivityAdapter(private var context: Context) :

    RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    var beers = mutableListOf<Product>()

    fun setBeerList(beers: List<Product>) {
        this.beers = beers.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, yu: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ListProductBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.binding.beerName.text = beer.name
        holder.binding.tagline.text = beer.tagline
        Glide.with(holder.itemView.context).load(beer.image_url).into(holder.binding.beerImage)

        holder.binding.root.setOnClickListener {
            var intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("productid", beer.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}

