package com.example.navigationbar.database

sealed interface ProductEvents{
    data class insertProdct(val monitoringProductsTable: MonitoringProductsTable):ProductEvents
}