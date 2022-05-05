package com.beer.product.domain

data class ProductDetailsDomainModel(
    val id: Int,
    val name: String,
    val tagline: String,
    val imageUrl: String,
    val ibu: Double,
    val abv: Double
)

