package com.example.secure_user_authentication.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.secure_user_authentication.domaine.model.MessageBarState
import com.example.secure_user_authentication.ui.theme.ErrorRed
import com.example.secure_user_authentication.ui.theme.InfoGreen
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun MessageBar(
    messageBarState: MessageBarState
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = messageBarState) {
        if (messageBarState.error != null) {
            errorMessage = when (messageBarState.error) {
                is SocketTimeoutException -> {
                    "Connection Timeout Exception."
                }
                is ConnectException -> {
                    "Internet Connection Unavailable."
                }
                else -> {
                    "${messageBarState.error.message}"
                }
            }
        }
        startAnimation = true
        delay(3000)
        startAnimation = false
    }

    AnimatedVisibility(
        visible = messageBarState.error != null && startAnimation
                || messageBarState.message != null && startAnimation,
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (messageBarState.error !== null) ErrorRed else InfoGreen)
                .padding(horizontal = 20.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                imageVector =
                if (messageBarState.error !== null) Icons.Default.Warning
                else Icons.Default.Check,
                contentDescription = "MessageBar Icon"
            )
            Divider(modifier = Modifier.width(12.dp), color = Color.Transparent)
            Text(
                text = if (messageBarState.error !== null) errorMessage
                else messageBarState.message.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.button.fontSize,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}