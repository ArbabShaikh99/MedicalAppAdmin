package com.example.medicalappadmin.UI_Layer.Screen.Product_Screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicalappadmin.R
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ui.theme.softWhiteColor


@Composable
fun ProductDetailScreen(
    productId : String,
    productViewModel: ProductViewModel

) {


    LaunchedEffect(Unit) {
        productViewModel.getSpecificProductID(productId)
    }
    val getSpecificProductState = productViewModel.getSpecificProduct.collectAsState()

    val context = LocalContext.current

  // Log.d("@product_detail", "ProductDetailScreen: ${getSpecificProductState.value.data?.body()!!.get(0)}")
    var productId by remember { mutableStateOf("") }



    when {
        getSpecificProductState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = softWhiteColor)
            }
        }

        getSpecificProductState.value.data != null -> {
            val productItem = getSpecificProductState.value.data!!.body()!![0] // Directly use the object
            Log.d("@product_detail", "getSpecificProductState-- Data : $productItem")
           // Log.d("@SpecificProduct", "Data Type: ${getSpecificProductState.value.data?.javaClass}")
            Log.d("@product_detail", "Raw JSON: ${getSpecificProductState.value.data}")



            //  val productItem = getSpecificProductState.value.data!!



                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(softWhiteColor)
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Bottom,

                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Id =  ",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Name = ${productItem.product_name}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Category = ${productItem.product_category}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Description = ${productItem.product_description}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Price =  ${productItem.product_price}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Stock = ${productItem.product_stock}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Expiry Date = ${productItem.product_expiry_date}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Rating = ${productItem.product_rating}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(softWhiteColor)
                            ) {
                                Text(
                                    text = "Product Power = ${productItem.product_power}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W400,
                                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                        color = Color.Black
                                    )
                                )
                            }

                        }
                    }
                }




        }
        getSpecificProductState.value.error != null -> {
            Log.d("@product_detail", "getSpecificProductState--Error: ${getSpecificProductState.value.error}")

            // Toast.makeText(context, getSpecificProductState.value.error, Toast.LENGTH_SHORT).show()
        }

    }
}