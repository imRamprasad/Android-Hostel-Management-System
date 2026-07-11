package com.neatroots.android.data.remote

import com.neatroots.android.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HostelApi {
    @GET("api/v1/health")
    suspend fun health(): ApiResponseDto<String>

    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequestDto): ApiResponseDto<AuthResponseDto>

    @POST("api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequestDto): ApiResponseDto<AuthResponseDto>

    @POST("api/v1/auth/google")
    suspend fun loginWithGoogle(@Body request: FirebaseLoginRequestDto): ApiResponseDto<AuthResponseDto>

    @POST("api/v1/auth/otp")
    suspend fun loginWithOtp(@Body request: FirebaseLoginRequestDto): ApiResponseDto<AuthResponseDto>

    @GET("api/v1/users")
    suspend fun users(): ApiResponseDto<List<UserDto>>
}