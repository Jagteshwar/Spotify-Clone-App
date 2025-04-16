package com.jagteshwar.spotifycloneapp.ui.feature.login

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState.Loading)
    val state: StateFlow<LoginState> = _state

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun fetchData() {

    }
}