package com.beer.product.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beer.product.data.database.dao.ProductDao
import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.database.entity.DatabaseProductListItem

@Database(entities = [DatabaseProductListItem::class, DatabaseProductDetails::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}



