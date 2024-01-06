package com.example.navigationbar.appscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationbar.R
import com.example.navigationbar.ui.theme.NavigationbarTheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Screen5(modifier: Modifier,navHostController: NavHostController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.search))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier
                .padding(horizontal = 12.dp, vertical = 20.dp)
                .weight(1f)
        ) {
            Button(
                onClick = { navHostController.popBackStack()},
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Add Product",
                    Modifier.padding(start = 0.dp, top = 10.dp, end = 0.dp, bottom = 0.dp),
                    Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(100)
                )

            }

        }

        Column(
            modifier = Modifier
                .weight(8f)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier,
                iterations = LottieConstants.IterateForever
            )
            Text(
                text = "Track",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(100)
            )
            Text(
                text = "Enter URL To Start Tracking", color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            SearchBar(Modifier.padding(8.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), horizontalArrangement = Arrangement.Center
            ) {
                Surface(onClick = { navHostController.navigate("web1") }, shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.amazon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop

                    )
                }


                Spacer(modifier = Modifier.padding(12.dp))

                Surface(onClick = { navHostController.navigate("web2") }, shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.flipkart),
                        contentDescription = null,
                        contentScale = ContentScale.Crop

                    )
                }


            }
            Text(
                text = "Expore Products???",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins))
            )

        }

        Surface(modifier.padding(8.dp), color = Color.Transparent) {
            NavBar(modifier.weight(7f),navHostController)
        }
    }
}

@Preview
@Composable
fun Screen() {
    NavigationbarTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }
        //Screen5(modifier = Modifier)


    }
}