//package com.example.learning_mvvm.mainUi.TodoUi.presenatation
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavController
//import com.example.learning_mvvm.BuildConfig
//
//@Composable
//fun TodoScreen(navController: NavController) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Welcome to the Paid Version!")
//
//        Button(
//            onClick = {
//                if (BuildConfig.FLAVOR == "paid") {
//                    // Navigate to the Paid Content Screen
//                    navController.navigate("paidContentScreen")
//                } else {
//                    // Navigate to the Payment Screen if the user is not subscribed
//                    navController.navigate("paymentScreen")
//                }
//            }
//        ) {
//            Text("▶ Play Now")
//        }
//    }
//}
