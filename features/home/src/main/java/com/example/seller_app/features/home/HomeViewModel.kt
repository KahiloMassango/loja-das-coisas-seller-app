package com.example.seller_app.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.data.SyncManager
import com.example.seller_app.core.data.repositories.SizeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genderRepository: GenderRepository
): ViewModel() {

    init {
        viewModelScope.launch {

        }
    }
}