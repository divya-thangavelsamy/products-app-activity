package com.beer.product.ui.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beer.product.data.repository.NetworkState
import com.example.product.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: ProductListAdapter

    private lateinit var emptyText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        homeBinding.recyclerView.adapter = adapter
        //emptyText = homeBinding.emptyTxt
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productList.observe(viewLifecycleOwner, Observer {
           // showEmptyList(it.isEmpty())
            adapter.submitList(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner) {
            homeBinding.progressBar.visibility =
                if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
        }

        adapter.clickListener.onItemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    it.id
                )
            )
        }
    }

    private fun showEmptyList(showList: Boolean) {
        if (!showList) {
            homeBinding.recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
        } else {
            homeBinding.recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        }
    }
}




