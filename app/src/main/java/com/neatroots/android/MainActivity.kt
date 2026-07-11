package com.neatroots.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neatroots.android.ui.theme.AndroidTheme
import com.neatroots.android.data.remote.RetrofitProvider
import com.neatroots.android.data.repository.DashboardRepository
import com.neatroots.android.ui.dashboard.DashboardRoute
import com.neatroots.android.ui.dashboard.DashboardViewModel
import com.neatroots.android.ui.dashboard.DashboardViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                val repository = DashboardRepository(RetrofitProvider.api)
                val dashboardViewModel: DashboardViewModel = viewModel(
                    factory = DashboardViewModelFactory(repository)
                )

                Scaffold { innerPadding ->
                    androidx.compose.foundation.layout.Box(
                        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
                    ) {
                        DashboardRoute(viewModel = dashboardViewModel)
                    }
                }
            }
        }
    }
}
