package com.ozan.listapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozan.listapp.data.models.Cart
import com.ozan.listapp.data.network.Status
import com.ozan.listapp.data.network.ViewState
import com.ozan.listapp.data.repository.CartRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepositoryImpl
) : ViewModel() {

    private val _uiStateCartList = MutableStateFlow<ViewState<List<Cart>>>(ViewState(Status.NONE))
    val uiStateCartList: StateFlow<ViewState<List<Cart>>> = _uiStateCartList

    private val _uiStateCartDetail = MutableStateFlow<ViewState<Cart>>(ViewState(Status.NONE))
    val uiStateCartDetail: StateFlow<ViewState<Cart>> = _uiStateCartDetail

    fun getCartList() {
        viewModelScope.launch {
            _uiStateCartList.value = ViewState(Status.LOADING)

            cartRepository
                .getCartList()
                .collect {
                    it.use(
                        onSuccess = { response ->
                            _uiStateCartList.value = ViewState(Status.SUCCESS, response)
                        },
                        onFailed = { error ->
                            _uiStateCartList.value = ViewState(Status.ERROR, error = error)
                        }
                    )
                }

        }
    }

    fun refreshCartList() {
        viewModelScope.launch {
            cartRepository
                .refreshCartList()
                .collect {
                    _uiStateCartList.value = ViewState(Status.ERROR, error = it)
                }
        }
    }

    fun getCartDetailFlow(productId: String) {
        viewModelScope.launch {
            _uiStateCartDetail.value = ViewState(Status.LOADING)

            cartRepository.getCartDetail(productId)
                .use(
                    onSuccess = { response ->
                        _uiStateCartDetail.value = ViewState(Status.SUCCESS, response)
                    },
                    onFailed = {
                        _uiStateCartDetail.value = ViewState(Status.ERROR, error = it)
                    }
                )
        }
    }
}