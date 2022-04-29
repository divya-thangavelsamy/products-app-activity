package com.beer.product.domain

import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.repository.ProductDetailsRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

interface DetailsUseCase {
    fun invoke(id: Int): Single<ProductDetailsResponse>
}

class DetailsUseCaseImpl @Inject constructor(private val repository: ProductDetailsRepositoryImpl) :
    DetailsUseCase {

    override operator fun invoke(id: Int): Single<ProductDetailsResponse> =
        repository.fetchProductDetails(id)
}
