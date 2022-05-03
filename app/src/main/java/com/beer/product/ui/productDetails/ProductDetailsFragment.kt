package com.beer.product.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.beer.product.data.repository.ResultOf
import com.example.product.R
import com.example.product.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentProductDetailsBinding

    private val viewModel: ProductDetailsViewModel by viewModels()

    private val args: ProductDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return detailsBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchProductDetails(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            this.productDetails.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ResultOf.Success -> {
                        with(detailsBinding) {
                            progressBar.hide()
                            toolbarDetails.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                            toolbarDetails.setNavigationOnClickListener {
                                findNavController().popBackStack()
                            }
                            toolbarDetails.setTitle(it.value.name)
                            beerName.text = it.value.name
                            beerTagline.text = it.value.tagline
                            imgF.load(it.value.imageUrl)
                            beerIBU.text = it.value.ibu.toString()
                            beerabv.text = it.value.abv.toString()
                        }
                    }
                    is ResultOf.Failure -> {
                        detailsBinding.errorText.show()
                    }
                    is ResultOf.Loading -> {
                        detailsBinding.progressBar.show()
                    }
                }
            })
        }
    }

    private fun View.show(): View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }

    private fun View.hide(): View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }
}



