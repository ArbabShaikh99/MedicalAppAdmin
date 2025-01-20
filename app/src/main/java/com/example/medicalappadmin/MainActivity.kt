package com.example.medicalappadmin

import Check
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicalappadmin.UI_Layer.Navigation.AppNavigation
import com.example.medicalappadmin.UI_Layer.Screen.DashBoard.SearchBar
import com.example.medicalappadmin.UI_Layer.Screen.Product_Screen.AddProductScreen
import com.example.medicalappadmin.ViewModel.StockViewModel
import com.example.medicalappadmin.ui.theme.MedicalAppAdminTheme
import com.example.medicalappadmin.ui.theme.softWhiteColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalAppAdminTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize().background(softWhiteColor).padding(innerPadding))


                   AppNavigation()
                    //AddProductScreen()
                   // Check()
                   // SearchBar(placeholderText = "Search products...")


                }

            }
        }
    }
}

