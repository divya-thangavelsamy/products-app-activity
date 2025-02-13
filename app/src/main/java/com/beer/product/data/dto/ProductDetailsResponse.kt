package com.beer.product.data.dto

data class ProductDetailsResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val image_url: String,
    val ibu: Double,
    val abv: Double
)
