package com.jagteshwar.spotifycloneapp.ui.feature.home

sealed class HomeEvent {
    data class ShowErrorMessage(val message: String): HomeEvent()
}