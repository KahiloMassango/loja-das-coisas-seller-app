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

    // Controls splash screen visibility
    var showSplashScreen by mutableStateOf(true)
        private set

    // Expose a Boolean flow that indicates if the user is logged in.
    // Defaulting to false means the UI initially considers the user unauthenticated.
    val isLoggedInFlow = accountRepository.isUserLoggedIn()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            false
        )

    init {
        // Trigger any necessary sync operations at startup.
        syncManager.syncCategories()
        syncManager.syncSizes()
        syncManager.syncColors()
        syncManager.syncGenders()

        // Use a delay to keep the splash screen visible until work is done.
        viewModelScope.launch {
            delay(1700)
            showSplashScreen = false
        }
    }

}

