package com.example.navigationbar.appscreens

import android.content.Context
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.room.Room
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.navigationbar.R
import com.example.navigationbar.database.MonitoringProductsTable
import com.example.navigationbar.database.ProductEvents
import com.example.navigationbar.database.ProductsDatabase
import com.example.navigationbar.navigationsystem.Screen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen4(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    screen4ViewModel: Screen4ViewModel,
    url: String,
) {
    println(url)
    screen4ViewModel.resetProduct(url)
    val productstate by screen4ViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier
                .padding(horizontal = 12.dp, vertical = 20.dp)
                .weight(1f)
        ) {
            Button(
                onClick = {
                    navHostController.navigate(Screen.S3.route,navOptions = NavOptions.Builder().setLaunchSingleTop(true).build())
                },
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn() {
                item {
                    Text(
                        text = productstate.productName,
                        Modifier.padding(start = 0.dp, top = 10.dp, end = 0.dp, bottom = 0.dp),
                        Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(100)
                    )
                }
            }


        }

        Surface(
            modifier = modifier
                .weight(4f)
                .fillMaxWidth()
                .padding(50.dp),
            color = Color.White,
            shape = RoundedCornerShape(20.dp),
        ) {
            SubcomposeAsyncImage(
                model = productstate.productImgUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit,
                loading = {
                    Box(modifier = Modifier.size(200.dp)) {
                        CircularProgressIndicator(modifier.size(30.dp).align(Alignment.Center))

                    }

                })

        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ), modifier = Modifier
                .weight(5f)
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {

            Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedCard(
                    Modifier
                        .padding(12.dp)
                        .weight(2f)
                ) {
                    Text(text = "Description", modifier = Modifier.padding(12.dp))
                }
                ElevatedCard(
                    Modifier
                        .padding(8.dp)
                        .weight(7f), elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp,
                    )
                ) {
                    LazyColumn() {
                        item {
                            Text(
                                text = productstate.productDescription,
                                modifier.padding(6.dp)
                            )
                            Text(
                                text = "Current Price:-" + productstate.productcPrice,
                                modifier = Modifier.padding(26.dp),
                                textAlign = TextAlign.Center,

                                )
                        }
                    }
                }
                ElevatedCard(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .weight(3f), elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp,
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    ) {
                        var expected: String by remember { mutableStateOf("") }

                        TextField(
                            value = expected,
                            onValueChange = { expected = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Enter Expected Price") },
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        val context = LocalContext.current
                        Button(

                            onClick = {
                                if (expected.isNotEmpty() && expected.all { char -> char.isDigit() }) {
                                    screen4ViewModel.uiState.value.productePrice = expected
                                    screen4ViewModel.onEvent()
                                    Toast.makeText(
                                        context,
                                        "Item has been Added",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navHostController.navigate(
                                        Screen.S3.route,
                                        navOptions = NavOptions.Builder().setLaunchSingleTop(true)
                                            .build()
                                    )
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Invalid input. Please enter a numeric value.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = "Track")

                        }
                    }
                }

            }

        }
    }
}