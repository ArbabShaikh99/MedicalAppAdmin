package com.example.medicalappadmin.UI_Layer.Screen.Stock_Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalappadmin.ViewModel.StockViewModel
import com.example.medicalappadmin.ui.theme.GreenColor
import com.example.medicalappadmin.ui.theme.softWhiteColor

@Composable
fun StockUserScreen(stockViewModel: StockViewModel = hiltViewModel() , navController: NavController) {

   val getAllStockResponseState = stockViewModel.getAllStockState.collectAsState()
    val getAllStockList = getAllStockResponseState.value.data?.body() ?: emptyList()

    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    LaunchedEffect(Unit) {
        stockViewModel.getAllStock()
    }

    when{
        getAllStockResponseState.value.loading ->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        getAllStockResponseState.value.Error != null ->{
            Toast.makeText(context, "${getAllStockResponseState.value.Error}", Toast.LENGTH_SHORT)
                .show()
        }
        getAllStockResponseState.value.data !=null ->{

            LazyColumn (
                modifier = Modifier.fillMaxSize()
                    .background(softWhiteColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyState){
                items(getAllStockList){ stockdata ->
                    StockUserCardView(stockdata, navController)

                }
            }

        }


    }
}