package com.example.secure_user_authentication.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DisplayAlertDialog(
    title: String = "Delete your account ?",
    message: String = "Are you sure you want to delete your account ?",
    openDialog: Boolean,
    onYesClicked: () -> Unit,
    onDialogClose: () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )

            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(onClick = onYesClicked) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = onDialogClose) {
                    Text(text = "No")
                }
            },
            onDismissRequest = onDialogClose
        )


    }
}