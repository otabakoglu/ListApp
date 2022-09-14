package com.ozan.listapp.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozan.listapp.ui.CartViewModel
import com.ozan.listapp.ui.composable.detail.CartDetailScreen
import com.ozan.listapp.ui.composable.list.CartListScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list",
    cartViewModel: CartViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list") {
            CartListScreen(cartViewModel) {
                navController.navigate("detail")
            }
        }
        composable("detail") {
            CartDetailScreen(cartViewModel)
        }
    }
}