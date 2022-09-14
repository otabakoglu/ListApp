package com.ozan.listapp.ui.composable.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.ui.CartViewModel
import com.ozan.listapp.ui.composable.ErrorView

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
            ErrorView(state.getErrorMessage())
        }
        else -> { // Loading
            Spacer(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}