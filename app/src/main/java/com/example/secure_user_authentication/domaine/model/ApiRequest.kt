package com.example.secure_user_authentication.domaine.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val tokenId: String
)
