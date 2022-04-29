package com.beer.product.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.beer.product.data.repository.ResultOf
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
        with(viewModel) {
            this.fetchProductDetails(args.id)
            this.productDetails.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ResultOf.Success -> {
                        with(detailsBinding) {
                         progressBar.visibility = View.GONE
                            beerName.text = it.value.name
                            beerTagline.text = it.value.tagline
                            imgF.load(it.value.image_url)
                            beerIBU.text = it.value.ibu.toString()
                            beerabv.text = it.value.abv.toString()
                        }
                    }
                    is ResultOf.Failure -> {
                        detailsBinding.errorText.visibility = View.VISIBLE
                    }
                    is ResultOf.Loading -> {
                        detailsBinding.progressBar.visibility = View.GONE
                    }
                }
            })
        }
    }
}



