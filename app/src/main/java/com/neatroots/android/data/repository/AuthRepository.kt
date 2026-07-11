package com.neatroots.android.data.repository

import com.neatroots.android.data.remote.HostelApi
import com.neatroots.android.data.remote.dto.*

class AuthRepository(private val api: HostelApi) {

    suspend fun login(request: LoginRequestDto) = api.login(request)

    suspend fun register(request: RegisterRequestDto) = api.register(request)

    suspend fun loginWithGoogle(idToken: String) = api.loginWithGoogle(FirebaseLoginRequestDto(idToken))

    suspend fun loginWithOtp(idToken: String) = api.loginWithOtp(FirebaseLoginRequestDto(idToken))
}
