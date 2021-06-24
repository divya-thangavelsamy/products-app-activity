package com.example.biirr.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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

    private lateinit var productname : TextView
    private lateinit var productTagline : TextView
    private lateinit var productIBU : TextView
    private lateinit var productABV : TextView
    private lateinit var productImage : ImageView

    private lateinit var binding: ActivityProductDetailsBinding

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productname = binding.beerName
        productTagline = binding.beerTagline
        productIBU = binding.beerIBU
        productABV = binding.beerabv
        productImage = binding.imgF
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val beerData = intent
        val productId = beerData.getIntExtra("productid", 0)
        viewModel.getProductDetails(productId)

        viewModel.productDetails.observe(this, Observer {
            val beerObj = it.firstOrNull()
            productname.text = beerObj?.name ?: "-"
            supportActionBar?.setTitle(beerObj?.name)
            productTagline.text = beerObj?.tagline ?: "-"

            Glide.with(this).load(beerObj?.image_url).into(productImage)
            val ibuValue = beerObj?.ibu ?: 0.0

            ibuValue.let {
                when {
                    it < 20 -> productIBU.text = "Smooth"
                    (it > 20 && it <= 50) -> productIBU.text = "Bitter"
                    it > 50 -> productIBU.text = "Hipster Plus"
                    else -> productIBU.text = "-"
                }
            }

            val abvP = beerObj?.abv?.div(100) ?: "-"
            val defaultFormat: NumberFormat = NumberFormat.getPercentInstance()
            defaultFormat.setMinimumFractionDigits(1)
            var abvPercentage = defaultFormat.format(abvP)
            productABV.text = abvPercentage
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}