package com.neatroots.android.data.remote.dto

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class RegisterRequestDto(
    val fullName: String,
    val email: String?,
    val password: String?,
    val phoneNumber: String?
)

data class FirebaseLoginRequestDto(
    val idToken: String
)

data class AuthResponseDto(
    val token: String?,
    val refreshToken: String?,
    val user: UserDto
)
