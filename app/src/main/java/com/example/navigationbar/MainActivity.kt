package com.example.navigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.navigationbar.appscreens.Screen3ViewModel
import com.example.navigationbar.appscreens.Screen4ViewModel
import com.example.navigationbar.database.MonitoringProductsTable
import com.example.navigationbar.database.ProductsDatabase
import com.example.navigationbar.navigationsystem.SetupNavigation
import com.example.navigationbar.ui.theme.NavigationbarTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db by lazy {
            Room.databaseBuilder(applicationContext,ProductsDatabase::class.java,"products.db").build()
        }
        val viewModel by viewModels<Screen4ViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return Screen4ViewModel(db.dao) as T
                    }
                }

            }
        )
        val viewModel1 by viewModels<Screen3ViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return Screen3ViewModel(db.dao) as T
                    }
                }

            }
        )


        setContent {
            window.statusBarColor= getColor(R.color.black)
            window.navigationBarColor=getColor(R.color.black)

            NavigationbarTheme {
                navController = rememberNavController()
                SetupNavigation(navHostController = navController, Modifier,viewModel,viewModel1)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()

                        )


                    SetupNavigation(navHostController = navController, modifier = Modifier,viewModel,viewModel1)



                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NavigationbarTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }

    }
}}

