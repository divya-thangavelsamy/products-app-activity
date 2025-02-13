package com.beer.product.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beer.product.data.repository.ResultOf
import com.beer.product.domain.ProductListDomainModel
import com.beer.product.domain.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productListUseCase: ProductListUseCase) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productList = MutableLiveData<ResultOf<List<ProductListDomainModel>>>()
    val productList: LiveData<ResultOf<List<ProductListDomainModel>>>
        get() = _productList


    fun fetchProductList() {
        _productList.postValue(ResultOf.Loading)
        compositeDisposable.add(
            productListUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> onResponse(data) },
                    { error -> onFailure(error) })
        )
    }

    private fun onFailure(error: Throwable) {
        _productList.postValue(ResultOf.Failure(error))
    }

    private fun onResponse(data: List<ProductListDomainModel>) {
        _productList.postValue(ResultOf.Success(data))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
