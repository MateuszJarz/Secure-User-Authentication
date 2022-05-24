package com.example.secure_user_authentication.domaine.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ApiResponse(
    val success : Boolean,
    val user: User? = null,
    val message: String? = null,
    @Transient
    val error: Exception? = null
)
