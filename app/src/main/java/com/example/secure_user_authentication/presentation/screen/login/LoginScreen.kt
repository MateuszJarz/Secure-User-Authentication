package com.example.secure_user_authentication.presentation.screen.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.secure_user_authentication.domaine.model.MessageBarState

@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = { LoginTopBar() },
        content = { LoginContent(
            signedInState = true ,
            messageBarState = MessageBarState(),
            onButtonClicked = {}
        )

        }
    )


}


