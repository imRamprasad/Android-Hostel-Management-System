package com.neatroots.android.ui.dashboard

import com.neatroots.android.data.remote.dto.UserDto

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(
        val healthMessage: String,
        val residentCount: Int,
        val residents: List<UserDto>
    ) : DashboardUiState

    data class Error(val message: String) : DashboardUiState
}