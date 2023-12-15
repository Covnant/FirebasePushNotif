package com.covenant.firebasepushnotif.screens.Register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.covenant.firebasepushnotif.extensions.application
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Register(navigator: DestinationsNavigator){
    val viewModel = viewModel{ RegisterViewModel(navigator = navigator, application = application)}
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