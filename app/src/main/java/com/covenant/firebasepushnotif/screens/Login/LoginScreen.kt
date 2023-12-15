package com.covenant.firebasepushnotif.screens.Login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.covenant.firebasepushnotif.screens.destinations.RegisterDestination
import com.covenant.firebasepushnotif.ui.theme.FirebasePushNotifTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun LoginScreen(
    navigator: DestinationsNavigator?,
    loginState: LoginState,
    loginStateChange: LoginStateChange,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Firebase Login",
            modifier = Modifier.padding(12.dp),
        )
        TextField(

            label = { Text(text = "Email")},
            value = loginState.email,
            onValueChange = loginStateChange.onEmailChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            isError = loginState.hasEmailError,
        )
        AnimatedVisibility(visible = loginState.hasEmailError) {
            Text(
                text = "Email cannot be empty",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            label = { Text(text = "Password")},
            value = loginState.password,
            onValueChange = loginStateChange.onPasswordChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            isError = loginState.hasPasswordError,
        )
        AnimatedVisibility(visible = loginState.hasPasswordError) {
            Text(
                text = "Password cannot be empty",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        FilledTonalButton(
            onClick =  loginStateChange.onLogin ,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Login")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Dont Have an account?")
            TextButton(onClick = { navigator?.navigate(RegisterDestination) }) {
                Text(text = "Register")
            }
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    FirebasePushNotifTheme {
        LoginScreen(
            loginState = LoginState(),
            navigator = null,
            loginStateChange = LoginStateChange(
                onEmailChange = {},
                onPasswordChange = {},
                onLogin = {},
            )
        )
    }
}