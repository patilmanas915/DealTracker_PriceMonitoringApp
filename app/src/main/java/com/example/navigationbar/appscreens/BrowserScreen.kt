package com.example.navigationbar.appscreens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.navigationbar.R
import com.example.navigationbar.network.callApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext





@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(navHostController: NavHostController, url: String,viewModel: Screen4ViewModel) {
    val loaderDialogScreen = remember { mutableStateOf(false) }
    val loader = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            // to play video on a web view
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    // show dialog
                    loaderDialogScreen.value = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // hide dialog
                    loaderDialogScreen.value = false
                }
            }

            loadUrl(url)
        }
    }

    var showLoaderDialog by remember { mutableStateOf(false) }

    Surface(Modifier.fillMaxSize(), color = Color.Black) {
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { if (webView.canGoBack()) webView.goBack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Button(
                    onClick = {
                        loader.value=true
                        val currentUrl = webView.url ?: ""
                        viewModel.isProductUrl(url = currentUrl) { result ->
                            if (result) {
                                val encodedUrl = Uri.encode(currentUrl)
                                navHostController.navigate(
                                    "product/$encodedUrl",
                                    navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
                                )
                                loader.value=false
                            } else {
                                Toast.makeText(context, "Invalid link", Toast.LENGTH_SHORT).show()
                                loader.value=false
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Track", fontWeight = FontWeight.Bold)
                }

                IconButton(onClick = {
                    showLoaderDialog = true
                    webView.reload()
                }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                }
            }


            // WebView
            AndroidView(
                factory = { webView },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )
        }
    }
    if (loader.value){
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(0.5f))) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text(text = "Product is loading")

            }

        }

    }

    // Loader Dialog
    if (showLoaderDialog) {
        if (loaderDialogScreen.value) {

            // Dialog function
            Dialog(
                onDismissRequest = {
                    loaderDialogScreen.value = false
                },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false // experimental
                )
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(20.dp))
                        //.........................Text: title
                        Text(
                            text = "Loading...",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //.........................Text : description
                        Text(
                            text = "Please wait",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            letterSpacing = 3.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                    }

                }
            }

        }
        // ... (your existing loader dialog code)
    }
}


