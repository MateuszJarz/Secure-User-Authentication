package com.example.secure_user_authentication.component


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.secure_user_authentication.R
import com.example.secure_user_authentication.ui.theme.LoadingBlue
import com.example.secure_user_authentication.ui.theme.Shapes


@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "Sing in with Google",
    secondaryText: String = "Please wait...",
    icon: Int = R.drawable.ic_google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    borderStrokeWidth: Dp = 1.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = LoadingBlue,
    onClick: () -> Unit
) {
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = loadingState) {
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clickable(enabled = !loadingState) {
                onClick()
            },
        shape = shape,
        color = backgroundColor,
        border = BorderStroke(width = 1.dp, borderColor),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing,
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Logo",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buttonText)
            if (loadingState){
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun GoogleButtonPrev() {
    GoogleButton(
        loadingState = true,
        onClick = {}
    )
}



