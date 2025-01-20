package com.example.medicalappadmin.UI_Layer.Screen.DashBoard

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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalappadmin.R
import com.example.medicalappadmin.UI_Layer.Navigation.AddProductRoute
import com.example.medicalappadmin.UI_Layer.Navigation.ShowHistoryScreenRoute
import com.example.medicalappadmin.UI_Layer.Navigation.StockUserRoutes
import com.example.medicalappadmin.ui.theme.softWhiteColor



@Composable
fun DashBoardScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AddProductRoute) },
                backgroundColor = Color.Black,
                        modifier = Modifier.size(43.dp),
              shape = RectangleShape

            ) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "Add Product",
                        tint = softWhiteColor,
                        modifier = Modifier.size(39.dp)
                    )
                }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(softWhiteColor)
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = "Menu Icon",
                        tint = Color.Black
                    )
                }

                Text(
                    text = "PharmaPe",
                    color = Color.Black,
                    fontSize = 31.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(onClick = { /* Handle settings click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_24),
                        contentDescription = "Settings Icon",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(
                placeholderText = "Search Product...",
                onSearch = { /* Handle search */ }
            )

            Spacer(modifier = Modifier.height(29.dp))

            Image(
                painter = painterResource(id = R.drawable.pharma), // Replace with your image
                contentDescription = "Pharma Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(29.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "All User Stocks : ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate(StockUserRoutes) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                ) {
                    Text("View All", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "All Sell History : ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate(ShowHistoryScreenRoute) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                ) {
                    Text("View All", color = Color.White)
                }
            }
        }
    }
}