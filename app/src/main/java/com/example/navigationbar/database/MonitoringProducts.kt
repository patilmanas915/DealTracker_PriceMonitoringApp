package com.example.navigationbar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "PRODUCTS")
data class MonitoringProductsTable(
    @PrimaryKey(autoGenerate = true)
    val productId:Int=0,
    val productName:String,
    val productImg:String,
    val productUrl: String,
    val currentPrice: String,
    val expectedPrice:String
)
