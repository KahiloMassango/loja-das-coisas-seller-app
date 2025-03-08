package com.example.seller_app.features.authentication.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

    var isLoggedIn by mutableStateOf(false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            accountRepository.login(email, password)
                .onSuccess {
                    Log.d("LoginViewModel", "login success")
                    isLoggedIn = true
                }
                .onFailure { e ->
                    Log.d("LoginViewModel", "login error: $e ")
                }
        }

    }

}