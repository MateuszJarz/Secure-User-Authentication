package com.example.secure_user_authentication.presentation.screen.login

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.secure_user_authentication.ui.theme.topAppBarBackgroundColor
import com.example.secure_user_authentication.ui.theme.topAppBarContentColor


@Composable
fun LoginTopBar() {

    TopAppBar(
        title = {
            Text(
                text = "Sing in",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Preview
@Composable
fun LoginTopBarPrev() {
    LoginTopBar()
}
