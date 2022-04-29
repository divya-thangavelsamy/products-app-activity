package com.beer.product.data.dto

import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.database.entity.DatabaseProductListItem

data class Product(
    val id: Int,
    val name: String,
    val tagline: String,
    val image_url: String,
    val ibu: Double,
    val abv: Double
)

fun List<Product>.asDatabaseListModel(): List<DatabaseProductListItem> {
    return map {
        DatabaseProductListItem(
            id = it.id,
            name = it.name,
            tagline = it.tagline,
            ibu = it.ibu,
            abv = it.abv,
            image = it.image_url
        )
    }
}


fun Product.asDatabaseModel(): DatabaseProductDetails {
    return DatabaseProductDetails(
        id = id,
        name = name,
        tagline = tagline,
        image = image_url,
        ibu = ibu,
        abv = abv,
    )

}


