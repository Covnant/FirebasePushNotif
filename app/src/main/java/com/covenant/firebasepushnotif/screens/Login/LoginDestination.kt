package com.covenant.firebasepushnotif.screens.Login

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.covenant.firebasepushnotif.extensions.application
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun Login(navigator: DestinationsNavigator){
    val viewModel = viewModel{LoginViewModel(application = application, navigator = navigator)}

    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LoginScreen(
        navigator = navigator,
        loginState = loginState,
        loginStateChange = LoginStateChange(
            onEmailChange =  viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onLogin = viewModel::onLogin,
        )
    )


}