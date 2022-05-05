package com.beer.product.ui.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.repository.ResultOf
import com.beer.product.domain.DetailsUseCaseImpl
import com.beer.product.domain.ProductDetailsDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val detailsUseCase: DetailsUseCaseImpl) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productDetails = MutableLiveData<ResultOf<ProductDetailsDomainModel>>()
    val productDetails: LiveData<ResultOf<ProductDetailsDomainModel>>
        get() = _productDetails

    fun fetchProductDetails(id: Int) {
        compositeDisposable.add(
            detailsUseCase(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { data -> onResponse(data) },
                    { error -> onFailure(error) })
        )
    }

    private fun onFailure(error: Throwable?) {
        _productDetails.postValue(ResultOf.Failure(error))
    }

    private fun onResponse(data: ProductDetailsDomainModel) {
        _productDetails.postValue(ResultOf.Success(data))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
