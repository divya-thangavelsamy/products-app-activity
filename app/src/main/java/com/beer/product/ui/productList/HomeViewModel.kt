package com.beer.product.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beer.product.data.dto.ProductListResponse
import com.beer.product.data.repository.ResultOf
import com.beer.product.domain.HomeUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(homeUseCase: HomeUseCaseImpl) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productList = MutableLiveData<ResultOf<List<ProductListResponse>>>()
    val productList: LiveData<ResultOf<List<ProductListResponse>>>
        get() = _productList


    init {
        _productList.postValue(ResultOf.Loading)
        compositeDisposable.add(
            homeUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { data -> onResponse(data) },
                    { error -> onFailure(error) })
        )
    }

    private fun onFailure(error: Throwable?) {
        _productList.postValue(ResultOf.Failure(error))
    }

    private fun onResponse(data: List<ProductListResponse>) {
        _productList.postValue(ResultOf.Success(data))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
