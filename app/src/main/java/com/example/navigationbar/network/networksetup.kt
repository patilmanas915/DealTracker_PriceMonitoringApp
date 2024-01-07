package com.example.navigationbar.network

import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


suspend fun callApi(link: String, product: String = "/scrape?url="): String? {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val url = "https://relaxing-safely-leech.ngrok-free.app$product$link"
        val request = Request.Builder().url(url).build()

        try {
            // Make the API request using OkHttp
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                print("success")
                response.body?.string()
            } else {
                print("fail")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            client.connectionPool.evictAll()
        }
    }
}


fun main() {
    runBlocking {
        launch(Dispatchers.IO) {
            try {
                val response = callApi("SMSSPRODUCT", "/smss_product?url=")
                print(response)
            } catch (e: Exception) {
                // Handle exceptions here
                e.printStackTrace()
            }
        }
    }
}




