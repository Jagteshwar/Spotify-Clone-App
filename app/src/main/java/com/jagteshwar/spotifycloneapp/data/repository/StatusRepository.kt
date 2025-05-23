package com.jagteshwar.spotifycloneapp.data.repository

import com.jagteshwar.spotifycloneapp.data.network.ApiService
import org.koin.core.annotation.Single

@Single
class StatusRepository(
    private val apiService: ApiService
) {

    suspend fun getStatus(): String {
        return apiService.getSomething().body()?.get("status") ?: "Failed"
    }
}