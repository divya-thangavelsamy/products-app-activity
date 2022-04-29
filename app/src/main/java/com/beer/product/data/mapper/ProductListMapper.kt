package com.beer.product.data.mapper

import com.beer.product.data.dto.Product
import com.beer.product.data.dto.ProductListResponse
import javax.inject.Inject

class ProductListMapper @Inject constructor() {
    fun map(item: List<Product>) : List<ProductListResponse> {
        return item.map {
            with(it) {
                ProductListResponse(
                    id = id,
                    name = name,
                    tagline = name,
                    image_url =  image_url
                )
            }
        }
    }
}
