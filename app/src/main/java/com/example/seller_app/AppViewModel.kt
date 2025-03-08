package com.example.seller_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.SyncManager
import com.example.seller_app.core.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val syncManager: SyncManager
) : ViewModel() {

    var showSplashScreen by mutableStateOf(true)
        private set

    init {

        syncManager.syncCategories()
        syncManager.syncSizes()
        syncManager.syncColors()
        syncManager.syncGenders()
    }

    val isLoggedInFlow = accountRepository.isUserLoggedIn()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            false
        ).also {
            viewModelScope.launch { delay(2000)
                showSplashScreen = false
            }
        }


}