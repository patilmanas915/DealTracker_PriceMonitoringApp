package com.example.navigationbar.notificationandworkmanager

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import com.example.navigationbar.DELAY_TIME_MILLIS
import com.example.navigationbar.MainActivity
import com.example.navigationbar.database.ProductsDatabase
import com.example.navigationbar.network.callApi
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import org.json.JSONObject
import java.util.concurrent.TimeUnit

private const val TAG = "BlurWorker"

class ProductWorker(
    ctx: Context,
    parameters: WorkerParameters,
) : CoroutineWorker(ctx, parameters) {

    override suspend fun doWork(): Result {
        println("running")
         val db by lazy {
            Room.databaseBuilder(applicationContext, ProductsDatabase::class.java, "products.db")
                .build()
        }
        return try {
            val productDao = db.dao
            val allProducts = productDao.getProduct().first()
            Log.e(
                TAG, allProducts.toString()
            )
            for (product in allProducts) {
                try {

                    val changeProduct = callApi(product.productUrl)
                    Log.e(
                        TAG, "1"
                    )
                    val jsonObject = JSONObject(changeProduct)
                    Log.e(
                        TAG, "1"
                    )
                    val price = jsonObject.getString("price")
                    val regex = Regex("[^0-9.]")
                    Log.e(
                        TAG, "1"
                    )
                    val numericPrice = regex.replace(price, "")
                    Log.e(
                        TAG, "12"
                    )
                    if (numericPrice.toDouble() <= product.expectedPrice.toDouble()) {
                        makeStatusNotification(
                            "${product.productName} price alter",
                            applicationContext
                        )
                    }
                    product.currentPrice = numericPrice
                    println(product.currentPrice + " is new price for " + product.productId)
                    productDao.insertProduct(product)
                } catch (e: Exception) {
                    print(e.printStackTrace())
                }
            }
            println("sc")
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }



}