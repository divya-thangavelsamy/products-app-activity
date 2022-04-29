package com.beer.product.data.mapper

import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.dto.Product

class ProductDetailsMapper {

     fun map(item: DatabaseProductDetails): Product {
        return Product(
                id = item.id,
                name = item.name,
                tagline = item.tagline,
                image_url = item.image,
                ibu = item.ibu,
                abv = item.abv
            )
        }
}
