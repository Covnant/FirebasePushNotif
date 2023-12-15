package com.covenant.firebasepushnotif.screens.Main


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.covenant.firebasepushnotif.extensions.application
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Main(navigator: DestinationsNavigator) {
    val viewModel = viewModel{MainViewModel(application = application, navigator = navigator)}
    with(viewModel){
        MainScreen(
            email = email,
            mainStateChange = MainStateChange(
                onLogout = ::onLogout,
            )
        )
    }
}