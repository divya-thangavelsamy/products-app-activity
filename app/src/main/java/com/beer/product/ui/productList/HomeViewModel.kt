package com.beer.product.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beer.product.data.dto.Product
import com.beer.product.data.repository.NetworkState
import com.beer.product.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) :
    ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList : LiveData<List<Product>>
    get() = _productList

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
        get() = _networkState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _networkState.postValue(NetworkState.LOADING)
            val list = homeUseCase.invoke()
            _productList.postValue(list)
            _networkState.postValue(NetworkState.LOADED)
        }
    }
}
