package com.example.medicalappadmin.UI_Layer.Navigation.BottomNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalappadmin.UI_Layer.Screen.All_User_Screen.UserApproveScreen
import com.example.medicalappadmin.UI_Layer.Screen.DashBoard.DashBoardScreen
import com.example.medicalappadmin.UI_Layer.Screen.Order_Screens.OrderApprovedScreen
import com.example.medicalappadmin.UI_Layer.Screen.Product_Screen.AllProductScreen
import com.example.medicalappadmin.ViewModel.ProductViewModel
import com.example.medicalappadmin.ViewModel.StockViewModel
import com.example.medicalappadmin.ui.theme.navigationColor

@Composable
fun BottomView (navController: NavController){

    val stockViewModel : StockViewModel = hiltViewModel()
   val productViewModel :ProductViewModel = hiltViewModel()
    //val navController : NavController

    var selected by remember {
        mutableIntStateOf(0)
    }

    val bottomNavItem = listOf(
        BotoomNavItem(
            name = "DashBoard",
            icon = Icons.Default.Check
        ),
        BotoomNavItem(
            name = "Order Approve",
            icon = Icons.Default.ShoppingCart
        ),
       BotoomNavItem(
            name = "User Approve",
            icon = Icons.Default.AccountBox
        )
        ,BotoomNavItem(
            name = "All Product",
            icon = Icons.Default.CheckCircle
        )
    )

    Box{
        Scaffold (
            bottomBar = {
                NavigationBar(containerColor = navigationColor,
//                    modifier = Modifier.height(400.dp)// Set the bottom navigation bar color to gray
                ) {
                    bottomNavItem.forEachIndexed { index, botoomNavItem ->
                        NavigationBarItem(
                            selected = selected == index ,
                            onClick = {selected = index },
                            icon = { Icon(botoomNavItem.icon , contentDescription = null, tint = Color.Black) },
                            label = { Text(botoomNavItem.name, color = Color.Black) }

                        )
                    }
                }

            }
        ){
                innerpadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerpadding)){
                when(selected){
                    0 -> DashBoardScreen(navController)
                    1 -> OrderApprovedScreen(navController)
                    2 -> UserApproveScreen()
                    3 -> AllProductScreen(productViewModel  , navController )


                }
            }
        }
    }




}