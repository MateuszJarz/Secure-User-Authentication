package com.example.secure_user_authentication.data.repository

import com.example.secure_user_authentication.data.remote.KtorApi
import com.example.secure_user_authentication.domaine.model.ApiRequest
import com.example.secure_user_authentication.domaine.model.ApiResponse
import com.example.secure_user_authentication.domaine.model.UserUpdate
import com.example.secure_user_authentication.domaine.repository.DataStoreOperations
import com.example.secure_user_authentication.domaine.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val ktorApi: KtorApi
) : Repository {
    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignedInState(signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperations.readSignedInState()
    }

    override suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse {
        return try {
            ktorApi.verifyTokenOnBackend(request = request)
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun getUserInfo(): ApiResponse {
        return try {
            ktorApi.getUserInfo()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun updateUserInfo(userUpdate: UserUpdate): ApiResponse {
        return try {
            ktorApi.updateUserInfo(userUpdate = userUpdate)
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun deleteUser(): ApiResponse {
        return try {
            ktorApi.deleteUser()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }

    override suspend fun clearSession(): ApiResponse {
        return try {
            ktorApi.clearSession()
        } catch (e: Exception) {
            ApiResponse(
                success = false,
                error = e
            )
        }
    }
}