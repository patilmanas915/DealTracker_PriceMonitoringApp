package com.example.navigationbar.appscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigationbar.R
import com.example.navigationbar.ui.theme.NavigationbarTheme


@Composable
fun Screen3(modifier: Modifier = Modifier, navHostController: NavHostController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(Modifier.weight(3f)) {
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
                        text = "Tracker",
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
            modifier
                .fillMaxSize()
                .weight(18f), color = Color.Transparent){
            LazyColumn(){
                val info= listOf("adsfl;jasdf","alsdfjlk","aklsfjasd","alsdfjlk","aklsfjasd","alsdfjlk","adsfl;jasdf","alsdfjlk","aklsfjasd","alsdfjlk","aklsfjasd","alsdfjlk")
                itemsIndexed(info) { index, hero ->
                    Itemsfor(
                        txt = hero,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

        }



        Surface(modifier.padding(8.dp), color = Color.Transparent) {
            NavBar(modifier.weight(7f),navHostController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Itemsfor(modifier: Modifier=Modifier,txt:String){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription =null,modifier.size(100.dp) )

            Column {
                Text(text = "Current Price: 40000")
                Text(text = "Expected Price: 5000")
            }
            IconButton(onClick = {},modifier.weight(1f)){
                Icon(imageVector = Icons.Default.Close, contentDescription =null)
            }
        }

    }
}

@Preview
@Composable
fun GreePreview() {
    NavigationbarTheme {
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }
        Screen3(navHostController =navController )

    }
}