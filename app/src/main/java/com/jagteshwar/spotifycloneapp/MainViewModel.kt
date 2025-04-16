package com.jagteshwar.spotifycloneapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagteshwar.spotifycloneapp.data.repository.StatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    val repository: StatusRepository
): ViewModel() {

    private val state = MutableStateFlow("")
    val status: StateFlow<String> = state.asStateFlow()

    init {
        getStatus()
    }

    private fun getStatus(){
        viewModelScope.launch {
          val result = repository.getStatus()
            state.value = result
        }
    }
}