package com.ozan.listapp.ui.composable.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.ui.CartViewModel

@Composable
fun CartListScreen(
    cartViewModel: CartViewModel,
    onNavigateToDetails: () -> Unit,
) {
    val state = cartViewModel.uiStateCartList.collectAsState().value

    when (state.status) {
        Status.SUCCESS -> {

            SwipeRefresh(
                state = rememberSwipeRefreshState(state.isLoading()),
                onRefresh = {
                    cartViewModel.refreshCartList()
                }
            ) {
                CartListContent(state.data ?: emptyList()) {
                    cartViewModel.getCartDetailFlow(it)
                    onNavigateToDetails.invoke()
                }
            }
        }
        Status.ERROR -> {
            Text(
                text = state.getErrorMessage(),
                modifier = Modifier.padding(16.dp)
            )
        }
        else -> {
            Spacer(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}