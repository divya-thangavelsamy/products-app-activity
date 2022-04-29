package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.database.ProductsDatabase
import com.beer.product.data.dto.Product
import com.beer.product.data.mapper.DatabaseListMapper
import com.beer.product.data.mapper.ProductListMapper
import javax.inject.Inject

class ProductListRepository @Inject constructor(
    private val webService: WebService,
    private val database: ProductsDatabase,
    private val productListMapper: ProductListMapper,
    private val databaseListMapper: DatabaseListMapper
) {

    private fun getProductsFromDb(): List<Product> {
        val cachedProductList = database.productDao.getProductsList()
        return productListMapper.map(cachedProductList)
    }

    suspend fun fetchProductList(): List<Product> {
        val products = webService.getProductList()
        database.productDao.insertAll(databaseListMapper.map(products))
        return getProductsFromDb()
    }
}
