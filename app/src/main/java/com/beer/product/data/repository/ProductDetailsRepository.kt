package com.beer.product.data.repository

import android.util.Log
import com.beer.product.data.api.WebService
import com.beer.product.data.database.ProductsDatabase
import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.dto.Product
import com.beer.product.data.mapper.DatabaseDetailsMapper
import com.beer.product.data.mapper.ProductDetailsMapper
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val webService: WebService,
    private val database: ProductsDatabase,
    private val productDetailsMapper: ProductDetailsMapper,
    private val databaseDetailsMapper: DatabaseDetailsMapper
) {

    private fun mapCachedDetails(dbDetails: DatabaseProductDetails): Product {
        return productDetailsMapper.map(dbDetails)
    }

    suspend fun fetchProductDetails(id: Int): Product {

        val dbData = database.productDao.getProductDetails(id)
        if (dbData != null) {
            return mapCachedDetails(dbData)
        } else {
            val productDetails = webService.getProductDetails(id).first()
            database.productDao.insertProductDetails(databaseDetailsMapper.map(productDetails))
            return productDetails
        }
    }
}
