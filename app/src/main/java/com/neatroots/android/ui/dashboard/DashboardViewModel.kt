package com.neatroots.android.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neatroots.android.data.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            try {
                val snapshot = withContext(Dispatchers.IO) {
                    repository.loadDashboard()
                }
                _uiState.value = DashboardUiState.Success(
                    healthMessage = snapshot.healthMessage,
                    residentCount = snapshot.residentCount,
                    residents = snapshot.residents
                )
            } catch (exception: Exception) {
                _uiState.value = DashboardUiState.Error(exception.message ?: "Unable to load backend data")
            }
        }
    }
}