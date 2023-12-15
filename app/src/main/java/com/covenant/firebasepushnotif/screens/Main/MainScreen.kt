package com.covenant.firebasepushnotif.screens.Main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.covenant.firebasepushnotif.ui.theme.FirebasePushNotifTheme

@Composable
fun MainScreen(
    email: String,
    mainStateChange: MainStateChange,
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = email)
        Spacer(modifier = Modifier.padding(16.dp))
        FilledTonalButton(onClick = mainStateChange.onLogout)
        {
            Text(text = "Logout")
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    FirebasePushNotifTheme {
        MainScreen(
            email = "",
            mainStateChange = MainStateChange(
                onLogout = {}
            )
        )
    }
}