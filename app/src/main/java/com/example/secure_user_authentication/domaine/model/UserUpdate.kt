package com.example.secure_user_authentication.domaine.model

import kotlinx.serialization.Serializable
@Serializable
data class UserUpdate(
val firstName: String,
val lastName: String
)

