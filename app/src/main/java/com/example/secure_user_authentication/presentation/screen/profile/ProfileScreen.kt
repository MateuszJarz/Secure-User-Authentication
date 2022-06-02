package com.example.secure_user_authentication.presentation.screen.profile

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.secure_user_authentication.domaine.model.ApiResponse
import com.example.secure_user_authentication.navigation.Screen
import com.example.secure_user_authentication.utill.RequestState
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val apiResponse by profileViewModel.apiResponse
    val messageBarState by profileViewModel.messageBarState
    val clearSessionResponse by profileViewModel.clearSessionResponse

    val user by profileViewModel.user
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastName

    Scaffold(
        topBar = {
            ProfileTopBar(onSave = {
                profileViewModel.updateUserInfo()
            }, onDelete = {
                profileViewModel.deleteUser()
            })

        },
        content = {
            ProfileContent(
                apiResponse = apiResponse,
                messageBarState = messageBarState,
                firstName = firstName,
                onFirstNameChanged = {
                    profileViewModel.updateFirstName(it)
                },
                lastName = lastName,
                onLastNameChanged = {
                    profileViewModel.updateLastName(it)
                },
                emailAddress = user?.emailAddress,
                profilePhoto = user?.profilePhoto,
                onSignOutClicked = {
                    profileViewModel.clearSession()
                }
            )
        }
    )
    val activity = LocalContext.current as Activity
    LaunchedEffect(key1 = clearSessionResponse) {
        if (clearSessionResponse is RequestState.Success &&
            (clearSessionResponse as RequestState.Success<ApiResponse>).data.success
        ) {
            val oneTapClient = Identity.getSignInClient(activity)
            oneTapClient.signOut()
            profileViewModel.saveSignedInState(signedIn = false)
            navigateToLoginScreen(navController = navController)
        }
    }

}

private fun navigateToLoginScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Login.route) {
        popUpTo(route = Screen.Profile.route) {
            inclusive = true
        }
    }
}