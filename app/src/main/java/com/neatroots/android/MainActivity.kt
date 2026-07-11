package com.neatroots.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neatroots.android.data.remote.RetrofitProvider
import com.neatroots.android.data.repository.AuthRepository
import com.neatroots.android.data.repository.DashboardRepository
import com.neatroots.android.ui.auth.AuthViewModel
import com.neatroots.android.ui.auth.AuthViewModelFactory
import com.neatroots.android.ui.auth.LoginRoute
import com.neatroots.android.ui.dashboard.DashboardRoute
import com.neatroots.android.ui.dashboard.DashboardViewModel
import com.neatroots.android.ui.dashboard.DashboardViewModelFactory
import com.neatroots.android.ui.theme.AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                val navController = rememberNavController()
                
                // Repositories
                val authRepository = AuthRepository(RetrofitProvider.api)
                val dashboardRepository = DashboardRepository(RetrofitProvider.api)

                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            val authViewModel: AuthViewModel = viewModel(
                                factory = AuthViewModelFactory(authRepository)
                            )
                            LoginRoute(
                                viewModel = authViewModel,
                                onLoginSuccess = {
                                    navController.navigate("dashboard") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("dashboard") {
                            val dashboardViewModel: DashboardViewModel = viewModel(
                                factory = DashboardViewModelFactory(dashboardRepository)
                            )
                            DashboardRoute(viewModel = dashboardViewModel)
                        }
                    }
                }
            }
        }
    }
}
