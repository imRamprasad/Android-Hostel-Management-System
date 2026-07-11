package com.neatroots.android.data.repository

import com.neatroots.android.data.remote.HostelApi
import com.neatroots.android.data.remote.dto.UserDto

data class DashboardSnapshot(
    val healthMessage: String,
    val residentCount: Int,
    val residents: List<UserDto>
)

class DashboardRepository(private val api: HostelApi) {

    suspend fun loadDashboard(): DashboardSnapshot {
        val health = api.health()
        val users = api.users()

        val residentList = users.data.orEmpty()
        return DashboardSnapshot(
            healthMessage = health.message,
            residentCount = residentList.size,
            residents = residentList
        )
    }
}