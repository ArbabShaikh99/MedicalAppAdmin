package com.example.medicalappadmin.UI_Layer.Screen.StockHistory_Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalappadmin.Data_Layer.Response.SellHistory.SellHistoryResponseItem
import com.example.medicalappadmin.UI_Layer.Navigation.DashBoardRoutes

@Composable
fun SellHistoryCardView(
    HistoryItems :SellHistoryResponseItem,
    navController: NavController
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF89CFF0), Color(0xFF4682B4))
                    )
                )
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Stock ID
                Text(
                    text = " ID: ${HistoryItems.id}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )

                // Product ID
                Text(
                    text = "Product ID: ${HistoryItems.product_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Order ID
                Text(
                    text = "Sell ID: ${HistoryItems.sell_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Product Name
                Text(
                    text = "Product Name: ${HistoryItems.product_name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                Text(
                    text = "User Name: ${HistoryItems.user_name}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                Text(
                    text = "User ID : ${HistoryItems.user_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Product Price
                Text(
                    text = "Price: $${HistoryItems.price}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                // Product Stock
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quantity: ",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        "${HistoryItems.quantity}", fontSize = 14.sp,
                        color = Color.White
                    )
                    LinearProgressIndicator(
                        progress = HistoryItems.quantity.toFloat() / 100,
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth(0.6f)
                            .padding(start = 8.dp),
                        color = Color.Green
                    )


                }

                // User Name


                // User ID
                Text(
                    text = "User ID: ${HistoryItems.user_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Certification Badge

                Text(
                    text = "Remaining Stock : ${HistoryItems.remaining_stock}",
                    fontSize = 14.sp,
                    color = Color.Yellow,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Total Amount : ${HistoryItems.total_amount}",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )


                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { navController.navigate(DashBoardRoutes) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text("Back", color = Color.Black)
                    }
                    Button(
                        onClick = { /* TODO: Handle edit */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text("Edit", color = Color.Black)
                    }
                }
            }
        }
    }

}