package com.beer.product.ui.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beer.product.data.dto.Product
import com.beer.product.data.repository.NetworkState
import com.beer.product.domain.DetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val detailsUseCase: DetailsUseCase) :
    ViewModel() {

    private val _productDetails = MutableLiveData<Product>()
    val productDetails : LiveData<Product>
        get() = _productDetails

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
        get() = _networkState

    fun fetchProductDetails(id: Int) = viewModelScope.launch (Dispatchers.IO) {
        _networkState.postValue(NetworkState.LOADING)
        val details = detailsUseCase.invoke(id)
        _productDetails.postValue(details)
        _networkState.postValue(NetworkState.LOADED)
    }
    }
