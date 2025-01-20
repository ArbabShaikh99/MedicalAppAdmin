package com.example.medicalappadmin.UI_Layer.Screen.Order_Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalappadmin.Data_Layer.Response.order.getAllOrderResponseItem
import com.example.medicalappadmin.R
import com.example.medicalappadmin.UI_Layer.Component.ImageComponent

@Composable
fun OrderCardPortion(
    data: getAllOrderResponseItem,
   onClick:()->Unit
) {

    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 8.dp, top = 8.dp)
            .padding(8.dp),
        // .height(110.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF6F6E8),
                            Color(0xFFEFEFDC)
                        ) // Light pastel gradient
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ImageComponent(
                    imageId = data.product_image_id,
                    imageSize = 100.dp,
                    padding = 8.dp,
                    shape = RectangleShape
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Product Name: ${data.product_name}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Product Price : ${data.product_price}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Order Date: ${data.order_date}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun TextBody(
    text :String,
    color:Color =Color.Black,
    fontSize :Int =16,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily =FontFamily(Font(R.font.roboto_regular))
){
Text(text, style = TextStyle(
    color =color,
    fontSize = fontSize.sp,
    fontWeight = fontWeight,
    fontFamily = fontFamily
))

}