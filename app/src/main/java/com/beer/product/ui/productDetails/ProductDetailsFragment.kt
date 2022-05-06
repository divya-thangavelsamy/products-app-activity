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
import com.beer.product.domain.ProductDetailsDomainModel
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
        if(savedInstanceState == null) {
            viewModel.fetchProductDetails(args.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            this.productDetails.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ResultOf.Success -> {
                        setToolBar(it)
                        setText(it)
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

    private fun setToolBar(result: ResultOf.Success<ProductDetailsDomainModel>) {
        with(detailsBinding.toolbarDetails) {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            setTitle(result.value.name)
        }
    }

    private fun setText(result: ResultOf.Success<ProductDetailsDomainModel>) {
        with(detailsBinding) {
            beerName.text = result.value.name
            beerTagline.text = result.value.tagline
            imgF.load(result.value.imageUrl)
            beerIBU.text = result.value.ibu.toString()
            beerabv.text = result.value.abv.toString()
            progressBar.hide()
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



