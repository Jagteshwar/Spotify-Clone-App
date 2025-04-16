package com.jagteshwar.spotifycloneapp.ui.feature.login

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: List<String>) : LoginState()
    data class Error(val message: String) : LoginState()
}