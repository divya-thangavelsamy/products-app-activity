package com.example.biirr.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.biirr.R
import com.example.biirr.adapter.ProductListAdapter
import com.example.biirr.databinding.ActivityMainBinding
import com.example.biirr.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProductListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView
    private lateinit var refreshView: SwipeRefreshLayout

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recycylerView
        emptyText = binding.emptyTxt
        refreshView = binding.swipeRefreshLayout
        adapter = ProductListAdapter(this)
        recyclerView.adapter = adapter

        viewModel.loadBeers()

        viewModel.beerData.observe(this, Observer {
            refreshView.isRefreshing = false
            showEmptyList(it.isEmpty())
            adapter.setBeerList(it)
        })

        viewModel.errorData.observe(this, { status ->
            refreshView.isRefreshing = false
            status.let {
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            }
        })

        val refreshListener = SwipeRefreshLayout.OnRefreshListener {
            refreshView.isRefreshing = true
            viewModel.loadBeers()
        }

        refreshView.setOnRefreshListener(refreshListener);
    }

    private fun showEmptyList(showList: Boolean) {
        if (!showList) {
            recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.my_menu, menu)
        val searchItem = menu.findItem(R.id.search_icon)
        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchedBeer(query.trim())
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
