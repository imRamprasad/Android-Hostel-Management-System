package com.neatroots.android.data.remote

import com.neatroots.android.data.remote.dto.ApiResponseDto
import com.neatroots.android.data.remote.dto.UserDto
import retrofit2.http.GET

interface HostelApi {
    @GET("api/v1/health")
    suspend fun health(): ApiResponseDto<String>

    @GET("api/v1/users")
    suspend fun users(): ApiResponseDto<List<UserDto>>
}