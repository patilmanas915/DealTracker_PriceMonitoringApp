package com.example.navigationbar.appscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.navigationbar.R
import com.example.navigationbar.ui.theme.NavigationbarTheme

@Composable
fun Screen1(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val fashion by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.fashion))
    val mobile by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.mobile))
    val grocery by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.grocery))
    val laptop by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.laptop))
    val book by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.boook))
    val homekit by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.homekit))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            Modifier
                .weight(3f)
                .fillMaxSize()
                .padding(16.dp),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(Modifier.weight(8f), color = Color.Transparent) {
                    Text(
                        text = "HEY! WELCOME TO DEALCRAKER",
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(100),
                        color = Color.White
                    )
                }

                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier
                        .weight(2f)
                        .size(110.dp),
                    tint = Color.White
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    mobile?.let {
                        CardProduct(
                            img = it,
                            name = "MOBILE",
                            navHostController = navHostController
                        )
                    }
                }
                item {
                    laptop?.let {
                        CardProduct(
                            img = it,
                            name = "LAPTOP",
                            navHostController = navHostController
                        )
                    }
                }
                item {
                    fashion?.let {
                        CardProduct(
                            img = it,
                            name = "FASHION",
                            navHostController = navHostController
                        )
                    }
                }
                item {
                    book?.let {
                        CardProduct(
                            img = it,
                            name = "BOOKS",
                            navHostController = navHostController
                        )
                    }
                }
                item {
                    homekit?.let {
                        CardProduct(
                            img = it,
                            name = "HOME & KITCHEN",
                            navHostController = navHostController
                        )
                    }
                }
                item {
                    grocery?.let {
                        CardProduct(
                            img = it,
                            name = "GROCERY",
                            navHostController = navHostController
                        )
                    }
                }


            }


        }

        Surface(modifier.padding(8.dp), color = Color.Transparent) {
            NavBar(modifier.weight(7f), navHostController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardProduct(
    modifier: Modifier = Modifier,
    img: LottieComposition,
    name: String,
    navHostController: NavHostController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedCard(
            onClick = {
                navHostController.navigate("sch/${name}",navOptions = NavOptions.Builder().setLaunchSingleTop(true).build())
            },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            )
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.size(200.dp)
            )
            {
                LottieAnimation(
                    composition = img,
                    modifier = Modifier.fillMaxSize(),
                    iterations = LottieConstants.IterateForever
                )

            }
            Text(
                text = name,
                fontFamily = FontFamily(Font(R.font.poppins)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true, uiMode = 2)
@Composable
fun GreetingPreview() {
    NavigationbarTheme {
        var navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center), contentScale = ContentScale.FillHeight
            )
            Screen1(navHostController = navController)
        }

    }
}