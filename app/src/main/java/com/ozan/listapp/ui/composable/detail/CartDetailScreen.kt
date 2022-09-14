package com.ozan.listapp.ui.composable.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.ui.CartViewModel

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