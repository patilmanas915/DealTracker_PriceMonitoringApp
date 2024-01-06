package com.example.navigationbar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MonitoringProductsTable::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val dao: MonitoringProductsTableDao

}

