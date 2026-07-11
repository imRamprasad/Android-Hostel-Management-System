package com.neatroots.android.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        DashboardUiState.Loading -> LoadingContent()
        is DashboardUiState.Error -> ErrorContent(message = state.message, onRetry = viewModel::refresh)
        is DashboardUiState.Success -> DashboardContent(
            healthMessage = state.healthMessage,
            residentCount = state.residentCount,
            residents = state.residents,
            onRefresh = viewModel::refresh
        )
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text(
            text = "Connecting Android to Spring Boot...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Backend connection failed", style = MaterialTheme.typography.headlineSmall)
        Text(text = message, modifier = Modifier.padding(top = 12.dp))
        Button(onClick = onRetry, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Retry")
        }
    }
}

@Composable
private fun DashboardContent(
    healthMessage: String,
    residentCount: Int,
    residents: List<com.neatroots.android.data.remote.dto.UserDto>,
    onRefresh: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Hostel ERP Sprint Dashboard", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(text = healthMessage, modifier = Modifier.padding(top = 8.dp))
                    Text(text = "MySQL-backed residents loaded: $residentCount", modifier = Modifier.padding(top = 4.dp))
                    Button(onClick = onRefresh, modifier = Modifier.padding(top = 16.dp)) {
                        Text(text = "Refresh from Backend")
                    }
                }
            }
        }

        item {
            Text(text = "Resident Sync Preview", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        }

        items(residents) { resident ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = resident.fullName ?: "Unnamed Resident", style = MaterialTheme.typography.titleMedium)
                    Text(text = resident.email ?: "No email")
                    Text(text = resident.phoneNumber ?: "No phone")
                }
            }
        }
    }
}