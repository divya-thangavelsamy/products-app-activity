package com.example.biirr.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biirr.model.ProductDetails
import com.example.biirr.service.WebService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val webService: WebService) :
    ViewModel() {

    private var disposable: Disposable? = null

    private val _productDetails = MutableLiveData<List<ProductDetails>>()
    val productDetails: LiveData<List<ProductDetails>>
        get() = _productDetails

    fun getProductDetails(productId: Int) {

        disposable = webService.getProductDetails(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
    }

    private fun onFailure(t: Throwable?) {
        Log.d("Error", t.toString())
    }

    private fun onResponse(response: List<ProductDetails>) {
        _productDetails.postValue(response)
    }

}
