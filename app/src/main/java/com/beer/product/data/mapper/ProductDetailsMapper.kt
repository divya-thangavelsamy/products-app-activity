package com.beer.product.data.mapper

import com.beer.product.data.dto.ProductDetails
import com.beer.product.data.dto.ProductDetailsResponse
import javax.inject.Inject

class ProductDetailsMapper @Inject constructor() {
    fun map(item: ProductDetails): ProductDetailsResponse {
        return with(item) {
            ProductDetailsResponse(
                id = id,
                name = name,
                tagline = tagline,
                image_url = image_url,
                ibu = ibu,
                abv = abv
            )
        }
    }
}
