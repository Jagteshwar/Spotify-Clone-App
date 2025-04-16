package com.jagteshwar.spotifycloneapp.ui.feature.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.jagteshwar.spotifycloneapp.ui.feature.widgets.ErrorScreen
import com.jagteshwar.spotifycloneapp.ui.feature.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is LoginEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    when (state.value) {
        is LoginState.Error -> {
            val errorMessage = (state.value as LoginState.Error).message
            ErrorScreen(
                errorMessage = errorMessage,
                primaryButton = "Retry",
                onPrimaryButtonClicked = {}
            )
        }

        is LoginState.Loading -> {
            LoadingScreen()
        }

        is LoginState.Success -> {

        }
    }
}