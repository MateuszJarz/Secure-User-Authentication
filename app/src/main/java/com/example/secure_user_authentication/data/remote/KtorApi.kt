package com.example.secure_user_authentication.data.remote

import androidx.activity.result.IntentSenderRequest
import com.example.secure_user_authentication.domaine.model.ApiRequest
import com.example.secure_user_authentication.domaine.model.ApiResponse
import com.example.secure_user_authentication.domaine.model.UserUpdate
import retrofit2.http.*

interface KtorApi {

    @POST("/token_verification")
    suspend fun verifyTokenOnBackend(
        @Body request: ApiRequest
    ): ApiResponse

    @GET("/get_user")
    suspend fun getUserInfo(): ApiResponse

    @PUT("/update_user")
    suspend fun updateUserInfo(
        @Body userUpdate : UserUpdate
    ): ApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUser(): ApiResponse

    @GET("/sign_out")
    suspend fun clearSession(): ApiResponse

    /*object GetUserInfo : Endpoint(path = "/get_user")
    object UpdateUserInfo : Endpoint(path = "/update_user")
    object DeleteUser : Endpoint(path = "/delete_user")
    object SignOutUser : Endpoint(path = "/sign_out")
    object Unauthorized : Endpoint(path = "/unauthorized")
    object Authorized : Endpoint(path = "/authorized")*/
}