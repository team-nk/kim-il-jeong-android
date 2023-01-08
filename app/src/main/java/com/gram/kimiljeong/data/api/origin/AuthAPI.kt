package com.gram.kimiljeong.data.api.origin

import com.gram.kimiljeong.data.model.remote.request.*
import com.gram.kimiljeong.data.model.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface AuthAPI {

    @PUT("/auth")
    suspend fun renewToken(
        @Header("Refresh-Token") refreshToken: String,
    ): Response<RenewTokenResponse>

    @GET("/mail")
    suspend fun sendVerificationMail(
        @Query("email") email: String,
    ): Response<Void>

    @POST("/user")
    suspend fun signUp(
        @Body request: SignUpRequest,
    ): Response<Void>

    @GET("/user")
    suspend fun getSelfInformation(): Response<SelfInformationResponse>

    @POST("/user/login")
    suspend fun login(
        @Body request: LoginRequest,
    ): Response<LoginResponse>

    @PATCH("/user/password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest,
    ): Response<Void>

    @PATCH("/user/birthday")
    suspend fun changeBirthdayRequest(
        @Body request: ChangeBirthdayRequest,
    ): Response<Void>

    @GET("/user/code")
    suspend fun checkVerificationCode(
        @Query("email") email: String,
        @Query("code") code: String,
    ): Response<BooleanResponse>

    @GET("/user/check")
    suspend fun checkIdDuplication(
        @Query("account-id") id: String,
    ): Response<BooleanResponse>

    @POST("/image")
    suspend fun uploadImage(
        @Body request: UploadImageRequest,
    ): Response<UploadImageResponse>
}
