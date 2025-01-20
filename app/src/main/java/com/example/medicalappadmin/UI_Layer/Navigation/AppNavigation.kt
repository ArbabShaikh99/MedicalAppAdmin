package com.example.medicalappadmin.UI_Layer.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalappadmin.UI_Layer.Navigation.BottomNavigation.BottomView
import com.example.medicalappadmin.UI_Layer.Screen.DashBoard.DashBoardScreen
import com.example.medicalappadmin.UI_Layer.Screen.Order_Screens.OrderApprovedScreen
import com.example.medicalappadmin.UI_Layer.Screen.Order_Screens.OrderDetailUI
import com.example.medicalappadmin.UI_Layer.Screen.Product_Screen.AddProductScreen
import com.example.medicalappadmin.UI_Layer.Screen.Product_Screen.ProductDetailScreen
import com.example.medicalappadmin.UI_Layer.Screen.StockHistory_Screen.ShowHistoryScreen
import com.example.medicalappadmin.UI_Layer.Screen.Stock_Screens.StockUserScreen
import com.example.medicalappadmin.ViewModel.OrderViewModel
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ViewModel.SellHistoryViewModel
import com.example.medicalappadmin.ViewModel.StockViewModel

@Composable
fun AppNavigation() {

     //val userViewmodel: UserViewmodel = hiltViewModel()
    val orderViewmodel: OrderViewModel = hiltViewModel()
    val productViewModel : ProductViewModel= hiltViewModel()
    val stockViewModel : StockViewModel= hiltViewModel()
    val sellHistoryViewModel : SellHistoryViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavigationRoute ) {

        composable<OrderApproveroutes> {
            OrderApprovedScreen(navController = navController)
        }

        composable<BottomNavigationRoute> {
            BottomView(navController)
        }

        composable<DashBoardRoutes> {
            DashBoardScreen(navController)
        }
        composable<StockUserRoutes> {
            StockUserScreen(stockViewModel =  stockViewModel,navController)
        }
        composable<ShowHistoryScreenRoute> {
            ShowHistoryScreen(sellHistoryViewModel = sellHistoryViewModel , navController)
        }
        composable<AddProductRoute> {
            AddProductScreen()
        }




        composable("order_details/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")
            if (orderId != null) {
                OrderDetailUI(
                    orderId = orderId,
                    navController = navController,
                    orderViewModel = orderViewmodel,
                    productViewModel = productViewModel,
                    stockViewModel = stockViewModel,
                    sellHistoryViewModel = sellHistoryViewModel
                )
            }
        }
//
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if(productId != null){
                ProductDetailScreen(
                    productId = productId,
                    productViewModel = productViewModel
                )
            }

        }

    }
}