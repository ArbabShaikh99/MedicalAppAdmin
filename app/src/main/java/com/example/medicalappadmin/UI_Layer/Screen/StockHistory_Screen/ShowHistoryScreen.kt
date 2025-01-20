package com.example.medicalappadmin.UI_Layer.Screen.StockHistory_Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medicalappadmin.UI_Layer.Component.TopAppBar
import com.example.medicalappadmin.UI_Layer.Screen.Stock_Screens.StockUserCardView
import com.example.medicalappadmin.ViewModel.SellHistoryViewModel
import com.example.medicalappadmin.ui.theme.softWhiteColor

@Composable
fun ShowHistoryScreen(
    sellHistoryViewModel: SellHistoryViewModel,
    navController: NavController
) {

    LaunchedEffect(Unit) {
        sellHistoryViewModel.getAllSellHistory()
    }
    val getAllSellHistoryState = sellHistoryViewModel.getAllSellHistoryState.collectAsState()
    val getAllSellHistoryList = getAllSellHistoryState.value.data?.body()
    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    when {
        getAllSellHistoryState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = softWhiteColor)
            }
        }

        getAllSellHistoryState.value.data != null -> {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(softWhiteColor)
            ) {
                Spacer(Modifier.height(8.dp))
                TopAppBar(headerName = "Sell History", isCloseIcon = true) {
                    navController.navigateUp()
                }
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .background(softWhiteColor),
                     horizontalAlignment = Alignment.CenterHorizontally,
                    state = lazyState
                ) {
                    items(getAllSellHistoryList!!){
                        SellHistoryCardView(it, navController)
                    }
                }
                }
                }



        getAllSellHistoryState.value.error != null -> {
            Toast.makeText(context, getAllSellHistoryState.value.error, Toast.LENGTH_SHORT).show()
        }
        }
}