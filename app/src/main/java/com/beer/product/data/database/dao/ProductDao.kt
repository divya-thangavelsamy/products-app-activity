package com.beer.product.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.database.entity.DatabaseProductListItem

@Dao
interface ProductDao {

    @Query("select * from DatabaseProductListItem")
    fun getProductsList(): List<DatabaseProductListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<DatabaseProductListItem>)

    @Query("select * from DatabaseProductDetails WHERE id = :id")
    fun getProductDetails(id: Int): DatabaseProductDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductDetails(productDetails: DatabaseProductDetails)
}
