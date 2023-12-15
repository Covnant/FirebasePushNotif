package com.covenant.firebasepushnotif.screens.Register

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
import com.covenant.firebasepushnotif.screens.destinations.LoginDestination
import com.covenant.firebasepushnotif.ui.theme.FirebasePushNotifTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun RegisterScreen(
    registerState: RegisterState,
    registerStateChange: RegisterStateChange,
    navigator: DestinationsNavigator?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Firebase Register",
            modifier = Modifier.padding(12.dp),
        )
        TextField(
            label = { Text(text = "Email") },
            value = registerState.email,
            onValueChange = registerStateChange.onEmailChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            isError = registerState.emailError,
        )
        AnimatedVisibility(visible = registerState.emailError) {
            Text(
                text = "Email cannot be empty",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            label = { Text(text = "Password") },
            value = registerState.password,
            onValueChange = registerStateChange.onPasswordChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            isError = registerState.passwordError,
        )
        AnimatedVisibility(visible = registerState.passwordError) {
            Text(
                text = "Password cannot be empty",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            label = { Text(text = "Repeat Password") },
            value = registerState.repeatedPassword,
            onValueChange = registerStateChange.onRepeatedPasswordChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            isError = registerState.repeatedPasswordError,
        )
        AnimatedVisibility(visible = !registerState.checkPassword) {
            Text(
                text = "Password does not match",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        FilledTonalButton(
            onClick = registerStateChange.onRegister,
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Register")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Already have an account?")
            TextButton(onClick = { navigator?.navigate(LoginDestination) }) {
                Text(text = "Login")
            }
        }


    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    FirebasePushNotifTheme() {
        RegisterScreen(
            registerState = RegisterState(),
            navigator = null,
            registerStateChange = RegisterStateChange(
                onRegister = {},
                onEmailChange = {},
                onRepeatedPasswordChange = {},
                onPasswordChange = {},
            )
        )
    }
}