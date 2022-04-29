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
import com.example.product.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        with(homeBinding) {
            recyclerView.adapter = adapter
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productList.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ResultOf.Success -> {
                    homeBinding.progressBar.visibility = View.GONE
                    adapter.submitList(it.value)
                }
                is ResultOf.Failure -> {
                    homeBinding.errorText.visibility = View.VISIBLE
                }
                is ResultOf.Loading -> {
                    homeBinding.progressBar.visibility = View.GONE
                }
            }
        })

        adapter.clickListener.onItemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    it.id
                )
            )
        }
    }
}




