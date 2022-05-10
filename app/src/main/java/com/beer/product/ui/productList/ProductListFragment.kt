package com.beer.product.ui.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beer.product.data.repository.ResultOf
import com.beer.product.utils.hide
import com.beer.product.utils.show
import com.example.product.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var homeBinding: FragmentProductListBinding

    private val viewModel: ProductListViewModel by viewModels()

    @Inject
    lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentProductListBinding.inflate(layoutInflater)

        with(homeBinding) {
            recyclerView.adapter = adapter
            return root
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.fetchProductList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultOf.Success -> {
                    homeBinding.toolbarHome.setTitle("Beer List")
                    homeBinding.progressBar.hide()
                    adapter.submitList(it.value)
                }
                is ResultOf.Failure -> {
                    homeBinding.errorText.show()
                }
                is ResultOf.Loading -> {
                    homeBinding.progressBar.show()
                }
            }
        })

        adapter.clickListener.onItemClick = {
            findNavController().navigate(
                ProductListFragmentDirections.actionHomeFragmentToDetailsFragment(
                    it.id
                )
            )
        }
    }
}




