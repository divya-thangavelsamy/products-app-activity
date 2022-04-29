package com.beer.product.domain

import com.beer.product.data.dto.ProductListResponse
import com.beer.product.data.repository.ProductListRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

interface HomeUseCase {
    operator fun invoke(): Single<List<ProductListResponse>>
}

class HomeUseCaseImpl @Inject constructor(private val repository: ProductListRepositoryImpl) :
    HomeUseCase {

    override operator fun invoke(): Single<List<ProductListResponse>> =
        repository.fetchProductList()
}
