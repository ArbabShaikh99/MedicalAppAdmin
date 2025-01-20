package com.example.medicalappadmin.UI_Layer.Screen.Stock_Screens

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalappadmin.Data_Layer.Response.stock.getAllStockResponseItem
import com.example.medicalappadmin.R
import com.example.medicalappadmin.UI_Layer.Navigation.DashBoardRoutes

//@Preview(showBackground = true)
@Composable
fun StockUserCardView(userStockResponseItem: getAllStockResponseItem , navController: NavController) {

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
                    text = "Stock ID: ${userStockResponseItem.id}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )

                // Product ID
                Text(
                    text = "Product ID: ${userStockResponseItem.product_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Order ID
                Text(
                    text = "Order ID: ${userStockResponseItem.order_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Product Name
                Text(
                    text = "Product Name: ${userStockResponseItem.product_name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                // Product Category
                Text(
                    text = "Category: ${userStockResponseItem.product_category}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Product Price
                Text(
                    text = "Price: $${userStockResponseItem.product_price}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                // Product Stock
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Stock: ",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        "${userStockResponseItem.product_stock}", fontSize = 14.sp,
                        color = Color.White
                    )
                    LinearProgressIndicator(
                        progress = userStockResponseItem.product_stock.toFloat() / 100,
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth(0.6f)
                            .padding(start = 8.dp),
                        color = Color.Green
                    )


                }

                // User Name
                Text(
                    text = "User Name: ${userStockResponseItem.user_name}",
                    fontSize = 14.sp,
                    color = Color.White
                )

                // User ID
                Text(
                    text = "User ID: ${userStockResponseItem.user_id}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Certification Badge

                Text(
                    text = "✔ Certified : ${userStockResponseItem.certified}",
                    fontSize = 14.sp,
                    color = Color.Yellow,
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
@Composable
fun TextView(
    text: String,
    color: Color = Color.Black,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text, style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
    )
}
