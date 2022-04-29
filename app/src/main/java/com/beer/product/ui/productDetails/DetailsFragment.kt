package com.beer.product.ui.productDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.product.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding

    private val viewModel: ProductDetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchProductDetails(args.id)
        viewModel.productDetails.observe(viewLifecycleOwner, Observer {
            detailsBinding.beerName.text = it.name
            detailsBinding.beerTagline.text = it.tagline
            detailsBinding.imgF.load(it.image_url)
            val ibuValue = it.ibu
            ibuValue.let {
                when {
                    it < 20 -> detailsBinding.beerIBU.text = "Smooth"
                    (it > 20 && it <= 50) -> detailsBinding.beerIBU.text = "Bitter"
                    it > 50 -> detailsBinding.beerIBU.text = "Hipster Plus"
                    else -> detailsBinding.beerIBU.text = "-"
                }
            }
            detailsBinding.beerabv.text = it.abv.toString()
        })
        viewModel.networkState.observe(viewLifecycleOwner, {})
    }
}



