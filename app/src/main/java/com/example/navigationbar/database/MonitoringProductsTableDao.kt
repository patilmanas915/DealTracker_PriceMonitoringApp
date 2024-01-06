package com.example.navigationbar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringProductsTableDao {
    @Upsert
    suspend fun insertProduct(monitoringProductsTable: MonitoringProductsTable)

    @Delete
    suspend fun deleteProduct(monitoringProductsTable: MonitoringProductsTable)

    @Query("SELECT * FROM PRODUCTS")
     fun getProduct(): Flow<List<MonitoringProductsTable>>
}