package com.ozan.listapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.ui.CartDetailItem
import com.ozan.listapp.ui.CartItem
import com.ozan.listapp.ui.CartListContent
import com.ozan.listapp.ui.CartViewModel
import com.ozan.listapp.ui.theme.ListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel.getCartListFlow()

        setContent {
            ListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppNavHost(cartViewModel = cartViewModel)
                }
            }
        }
    }
}

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

@Composable
fun CartListScreen(
    cartViewModel: CartViewModel,
    onNavigateToDetails: () -> Unit,
) {
    val state = cartViewModel.uiStateCartList.collectAsState().value

    when (state.status) {
        Status.SUCCESS -> {
            CartListContent(state.data?.products ?: emptyList()) {
                cartViewModel.getCartListFlow(it)
                onNavigateToDetails.invoke()
            }
        }
        Status.ERROR -> {
            Text(
                text = state.getErrorMessage(),
                modifier = Modifier.padding(16.dp)
            )
        }
        else -> {
            Text(
                text = "Loading",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun CartDetailScreen(
    cartViewModel: CartViewModel
) {
    val state = cartViewModel.uiStateCartDetail.collectAsState().value

    when (state.status) {
        Status.SUCCESS -> {
            state.data?.let {
                CartDetailItem(it)
            }
        }
        Status.ERROR -> {
            Text(
                text = state.getErrorMessage(),
                modifier = Modifier.padding(16.dp)
            )
        }
        else -> {
            Text(
                text = "Loading",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

