package com.covenant.firebasepushnotif.screens.Main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.covenant.firebasepushnotif.extensions.application
import com.covenant.firebasepushnotif.screens.destinations.LoginDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Main(navigator: DestinationsNavigator) {
    val viewModel = viewModel{MainViewModel(application = application, navigator = navigator)}
    with(viewModel){
        val mainState by mainState.collectAsStateWithLifecycle()
        MainScreen(
            email = email,
            navigator = navigator,
            mainState = mainState,
            mainStateChange = MainStateChange(
                onLogout = ::onLogout,
            )
        )
    }
}