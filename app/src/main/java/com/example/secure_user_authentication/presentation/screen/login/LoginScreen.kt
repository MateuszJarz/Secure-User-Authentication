package com.example.secure_user_authentication.presentation.screen.login

import android.app.Activity
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.secure_user_authentication.domaine.model.ApiRequest
import com.example.secure_user_authentication.domaine.model.ApiResponse
import com.example.secure_user_authentication.navigation.Screen
import com.example.secure_user_authentication.presentation.screen.common.StartActivityForResult
import com.example.secure_user_authentication.presentation.screen.common.signIn
import com.example.secure_user_authentication.utill.RequestState
import kotlin.math.log

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    loginViewModel.saveSignedInState(signedIn = true)
                }
            )

        }
    )
    val activity = LocalContext.current as Activity

    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            loginViewModel.verifyTokenOnBackend(request = ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        },

        ) { activityLauncher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
            )
        }
    }
    LaunchedEffect(key1 = apiResponse){
        when(apiResponse){
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (response){
                    navigateToProfileScreen(navController = navController)
                }else{
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }

}

private fun navigateToProfileScreen(
    navController: NavHostController
){
    navController.navigate(route = Screen.Profile.route){
        popUpTo(route = Screen.Login.route){
            inclusive = true
        }
    }
}


