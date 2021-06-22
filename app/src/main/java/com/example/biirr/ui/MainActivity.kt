package com.example.biirr.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.biirr.adapter.MainActivityAdapter
import com.example.biirr.databinding.ActivityMainBinding
import com.example.biirr.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    val adapter = MainActivityAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycylerView.adapter = adapter

        viewModel.beerData.observe(this, Observer {
            Log.d(TAG, "OnCreate : $it")
            adapter.setBeerList(it)
        })

        viewModel.errorData.observe(this, { status ->
            status.let {
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loadBeers()
    }

}
