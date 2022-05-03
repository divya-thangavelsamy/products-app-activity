package com.beer.product.data.mapper

import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.domain.ProductDetailsDomainModel
import javax.inject.Inject

class ProductDetailsMapper @Inject constructor() {
    fun map(item: ProductDetailsResponse): ProductDetailsDomainModel {
        return with(item) {
            ProductDetailsDomainModel(
                id = id,
                name = name,
                tagline = tagline,
                imageUrl = image_url,
                ibu = ibu,
                abv = abv
            )
        }
    }
}
