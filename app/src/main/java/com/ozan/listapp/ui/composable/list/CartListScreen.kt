package com.ozan.listapp.ui.composable.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.ui.CartViewModel
import com.ozan.listapp.ui.composable.ErrorView

@Composable
fun CartListScreen(
    cartViewModel: CartViewModel,
    onNavigateToDetails: () -> Unit,
) {

    val state = cartViewModel.uiStateCartList.collectAsState().value

    SwipeRefresh(
        state = rememberSwipeRefreshState(state.isLoading()),
        onRefresh = {
            cartViewModel.refreshCartList()
        }
    ) {
        when (state.status) {
            Status.SUCCESS -> {

                CartListContent(state.data ?: emptyList()) {
                    cartViewModel.getCartDetailFlow(it)
                    onNavigateToDetails.invoke()
                }
            }
            Status.ERROR -> {
                ErrorView(state.getErrorMessage())
            }
            else -> { // Loading
                Spacer(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}