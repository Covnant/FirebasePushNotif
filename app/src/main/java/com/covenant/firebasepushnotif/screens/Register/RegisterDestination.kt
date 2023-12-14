package com.covenant.firebasepushnotif.screens.Register

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Register(navigator: DestinationsNavigator){
    val viewModel = viewModel{ RegisterViewModel(navigator)}
    with(viewModel){
        val registerState by registerState.collectAsStateWithLifecycle()

        RegisterScreen(
            registerState = registerState,
            navigator = navigator,
            registerStateChange = RegisterStateChange(
                onRegister = ::onRegister,
                onPasswordChange = ::onPasswordChange,
                onRepeatedPasswordChange = ::onRepeatedPasswordChange,
                onEmailChange = ::onEmailChange,
            )
        )

    }
}