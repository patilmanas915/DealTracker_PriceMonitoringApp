package com.example.navigationbar.appscreens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navigationbar.database.MonitoringProductsTable
import com.example.navigationbar.database.MonitoringProductsTableDao
import com.example.navigationbar.database.ProductEvents
import com.example.navigationbar.database.ProductsDatabase
import com.example.navigationbar.network.callApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject


class Screen4ViewModel(
    private val dao: MonitoringProductsTableDao
) : ViewModel() {

    fun onEvent() {

        viewModelScope.launch {
            dao.insertProduct(
                monitoringProductsTable = MonitoringProductsTable(
                    productImg = _uiState.value.productImgUrl,
                    productUrl = _uiState.value.productUrl,
                    productName = _uiState.value.productName,
                    currentPrice = _uiState.value.productcPrice,
                    expectedPrice = _uiState.value.productePrice
                )
            )
        }
    }

    fun isProductUrl(url: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = callApi(url)
                val jsonObject = response?.let { JSONObject(it) }
                val compare = jsonObject?.getString("name")
                if (compare == "null") {
                    onComplete(false)
                } else
                    onComplete(response != null)
            } catch (e: Exception) {
                // Handle exceptions, e.g., network errors
                e.printStackTrace()
                onComplete(false)
            }
        }
    }


    private val _uiState = MutableStateFlow(ProductState())
    val uiState: StateFlow<ProductState> = _uiState.asStateFlow()
    fun resetProduct(url: String) {
        viewModelScope.launch {
            try {
                val result = callApi(url)
                val jsonObject = JSONObject(result)
                val imageUrl = jsonObject.getString("images")
                val price = jsonObject.getString("price")
                val regex = Regex("[^0-9.]")
                val numericPrice = regex.replace(price, "")
                val title = jsonObject.getString("name")
                var description: String = ""
                if (jsonObject.has("about_item")) {
                    description = jsonObject.getString("about_item")
                } else {
                    description = jsonObject.getString("highlights")
                }
                _uiState.value = ProductState(
                    productName = title,
                    productImgUrl = imageUrl,
                    productcPrice = numericPrice,
                    productDescription = description,
                    productUrl = url
                )
                println(url)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}