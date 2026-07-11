package com.neatroots.android.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neatroots.android.data.remote.SessionManager
import com.neatroots.android.data.remote.dto.LoginRequestDto
import com.neatroots.android.data.remote.dto.RegisterRequestDto
import com.neatroots.android.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.login(LoginRequestDto(email, password))
                }
                if (response.success && response.data != null) {
                    SessionManager.token = response.data.token
                    SessionManager.refreshToken = response.data.refreshToken
                    _uiState.value = AuthUiState.Success(response.message)
                } else {
                    _uiState.value = AuthUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(fullName: String, email: String, password: String, phoneNumber: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.register(RegisterRequestDto(fullName, email, password, phoneNumber))
                }
                if (response.success && response.data != null) {
                    SessionManager.token = response.data.token
                    SessionManager.refreshToken = response.data.refreshToken
                    _uiState.value = AuthUiState.Success(response.message)
                } else {
                    _uiState.value = AuthUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.loginWithGoogle(idToken)
                }
                if (response.success && response.data != null) {
                    SessionManager.token = response.data.token
                    SessionManager.refreshToken = response.data.refreshToken
                    _uiState.value = AuthUiState.Success(response.message)
                } else {
                    _uiState.value = AuthUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Google login failed")
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
