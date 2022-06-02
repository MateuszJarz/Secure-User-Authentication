package com.example.secure_user_authentication.presentation.screen.profile

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.secure_user_authentication.R
import com.example.secure_user_authentication.component.DisplayAlertDialog
import com.example.secure_user_authentication.ui.theme.topAppBarBackgroundColor
import com.example.secure_user_authentication.ui.theme.topAppBarContentColor

@Composable
fun ProfileTopBar(
    onSave: () -> Unit,
    onDelete: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ProfileTopBarActions(onSave = onSave, onDelete = onDelete)
        }
    )

}

@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit,
    onDelete: () -> Unit,
) {

    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        openDialog = openDialog,
        onYesClicked = onDelete,
        onDialogClose = { openDialog = false })


    SaveAction(onSave = onSave)
    DeleteAction(onDelete = {openDialog = true})


}

@Composable
fun SaveAction(
    onSave: () -> Unit,
) {
    IconButton(onClick = onSave) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = "save_icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDelete: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = "vertical_menu",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onDelete()
            }) {
            Text(
                text = "Delete Account",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview
@Composable
fun ProfileTopBarPrev() {
    ProfileTopBar(
        onSave = {}, onDelete = {})

}