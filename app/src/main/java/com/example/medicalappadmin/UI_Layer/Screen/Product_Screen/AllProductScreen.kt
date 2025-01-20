package com.example.medicalappadmin.UI_Layer.Screen.Product_Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalappadmin.UI_Layer.Navigation.ProductDetailRoute
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ui.theme.GreenColor
import com.example.medicalappadmin.ui.theme.softWhiteColor

@Composable
fun AllProductScreen(productViewModel: ProductViewModel = hiltViewModel() , navController: NavController) {

    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }
    val getAllProductState = productViewModel.getAllProducts.collectAsState()
    val allProductList = getAllProductState.value.data ?.body() ?: emptyList()
    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when {
        getAllProductState.value.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            )  {CircularProgressIndicator(color = softWhiteColor)}
        }

        getAllProductState.value.data != null -> {
            Log.d("@product_detail", "AllProductScreen: ${getAllProductState.value.data}")

            Box(
                modifier = Modifier.fillMaxSize().background(softWhiteColor),
                contentAlignment = Alignment.BottomEnd
            ) {

//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(softWhiteColor),
//                    horizontalAlignment = Alignment.Start,
//                    verticalArrangement = Arrangement.Top
//                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                                state = lazyState
                    ) {
                        items(allProductList) { data ->
                            ProductCard(data){
                                    navController.navigate(ProductDetailRoute(productId = data.product_id).route)
                                }

                        }

                    }
               // }
            }
        }

        getAllProductState.value.error != null -> {
//            Log.d("TAG", "AllProductShowScreen: ${getAllProductState.value.error}")

                Toast.makeText(context, getAllProductState.value.error, Toast.LENGTH_SHORT).show()
        }
    }
}


