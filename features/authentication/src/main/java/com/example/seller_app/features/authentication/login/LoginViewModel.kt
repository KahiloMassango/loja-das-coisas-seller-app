package com.example.seller_app.features.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.AccountRepository
import com.example.seller_app.core.data.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {


    var isLoggedIn by mutableStateOf(false)

    var message: String? by mutableStateOf(null)

    fun login(identifier: String, password: String) {
        if (!networkMonitor.hasNetworkConnection()) {
            message = "Sem conexão com a internet"
            return
        }
        viewModelScope.launch {
            accountRepository.login(identifier, password)
                .onSuccess {
                    isLoggedIn = true
                }
                .onFailure { e ->
                    message = e.message
                }
        }
    }

    fun messageShown() {
        message = null
    }

}