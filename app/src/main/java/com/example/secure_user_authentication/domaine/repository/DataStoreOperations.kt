package com.example.secure_user_authentication.domaine.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreOperations {

    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>
}