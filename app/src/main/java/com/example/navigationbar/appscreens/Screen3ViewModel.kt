package com.example.navigationbar.appscreens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationbar.database.MonitoringProductsTable
import com.example.navigationbar.database.MonitoringProductsTableDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Screen3ViewModel(private val dao: MonitoringProductsTableDao):ViewModel() {

    private val _listOfProduct = MutableStateFlow<List<MonitoringProductsTable>>(emptyList())
    val listOfProduct: StateFlow<List<MonitoringProductsTable>> = _listOfProduct.asStateFlow()
    init {
        setProduct()
    }
    fun setProduct(){
        viewModelScope.launch {
            dao.getProduct().collect { productsFromDatabase ->
                // Update the StateFlow with the fetched data
                _listOfProduct.value = productsFromDatabase
            }

        }

    }
    fun removeProduct(monitoringProductsTable: MonitoringProductsTable){
        viewModelScope.launch {
            dao.deleteProduct(monitoringProductsTable)

        }
    }

}