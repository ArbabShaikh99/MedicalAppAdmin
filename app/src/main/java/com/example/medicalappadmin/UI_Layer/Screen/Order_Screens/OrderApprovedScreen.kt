package com.example.medicalappadmin.UI_Layer.Screen.Order_Screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalappadmin.UI_Layer.Navigation.OrderDetailScreenRoute
import com.example.medicalappadmin.ViewModel.OrderViewModel
import com.example.medicalappadmin.ui.theme.GreenColor

@Composable
fun OrderApprovedScreen(navController: NavController ,orderviewModel: OrderViewModel = hiltViewModel()
) {


    val getAllOrderResponseState = orderviewModel.getAllOrderState.collectAsState()
    val getAllorderData = getAllOrderResponseState.value.Data?.body() ?: emptyList()

    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when {
        getAllOrderResponseState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = GreenColor)
            }
        }

        getAllOrderResponseState.value.Data != null -> {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyState
            ) {
                items(getAllorderData.reversed()) { datas ->
                    OrderCardPortion(datas) {


                        navController.navigate(OrderDetailScreenRoute(orderId = datas.order_id).route)
                        //   navController.navigate(orderdetailroutes)
                    }
                }
            }
        }


        getAllOrderResponseState.value.Error != null -> {
            Toast.makeText(
                context, "${getAllOrderResponseState.value.Error}",
                Toast.LENGTH_SHORT
            )
        }
    }
}


