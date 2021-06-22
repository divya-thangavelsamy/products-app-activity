package com.example.biirr.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biirr.model.Product
import com.example.biirr.service.Constants
import com.example.biirr.service.WebService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val webService: WebService) : ViewModel() {

    private var disposable: Disposable? = null

    private val _beerData = MutableLiveData<List<Product>>()
    val beerData : LiveData<List<Product>> = _beerData

    private val _errorData  = MutableLiveData<String>()
    val errorData : LiveData<String> = _errorData

    fun loadBeers() {
        disposable = webService.getBeerInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response -> onResponse(response) }, { error -> onFailure(error) })
    }

    private fun onFailure(error: Throwable?) {
        //TODO This is a basic connectivity check
        _errorData.postValue(Constants.NETWORK_ERROR_MSG)
    }

    private fun onResponse(response: List<Product>?) {
        setProduct(response)
    }

    fun setProduct(response: List<Product>?) {
        _beerData.postValue(response!!)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}