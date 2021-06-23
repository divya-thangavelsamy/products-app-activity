package com.example.biirr.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.biirr.databinding.ActivityProductDetailsBinding
import com.example.biirr.viewModel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Beer Details")

        val beerData = intent
        val productId = beerData.getIntExtra("productid", 0)

        viewModel.productDetails.observe(this, Observer {

            binding.beerName.text = it.firstOrNull()?.name

            supportActionBar?.setTitle(it.firstOrNull()?.name)

            binding.beerTagline.text = it.firstOrNull()?.tagline

            Glide.with(this).load(it.firstOrNull()?.image_url).into(binding.imgF)
            val ibuval = it.firstOrNull()?.ibu?: 0.0

            ibuval.let {
                when {
                    it > 20 -> binding.beerIBU.text = "Smooth"
                    (it > 20 && it <= 50) -> binding.beerIBU.text = "Bitter"
                    it > 50 -> binding.beerIBU.text = "Hipster Plus"
                    else -> binding.beerIBU.text = "-"
                }
            }

            val abvP = it.firstOrNull()!!.abv?.div(100) ?: "-"
            val defaultFormat: NumberFormat = NumberFormat.getPercentInstance()
            defaultFormat.setMinimumFractionDigits(1)
            var abvPercentage = defaultFormat.format(abvP)
            binding.beerabv.text = abvPercentage
        })

        viewModel.getProductDetails(productId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}