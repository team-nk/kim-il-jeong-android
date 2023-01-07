package app.junsu.kimiljeong.data.api.remote

import app.junsu.kimiljeong.data.model.remote.request.*
import app.junsu.kimiljeong.data.model.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface AuthAPI {

    @PUT("/auth")
    fun renewToken(
        @Header("Refresh-Token") refreshToken: String,
    ): Response<RenewTokenResponse>

    @GET("/mail")
    fun sendVerificationMail(
        @Query("email") email: String,
    ): Response<Void>

    @POST("/user")
    fun signUp(
        @Body request: SignUpRequest,
    ): Response<Void>

    @GET("/user")
    fun getSelfInformation(): Response<SelfInformationResponse>

    @POST("/user/login")
    fun login(
        @Body request: LoginRequest,
    ): Response<LoginResponse>

    @PATCH("/user/password")
    fun changePassword(
        @Body request: ChangePasswordRequest,
    ): Response<Void>

    @PATCH("/user/birthday")
    fun changeBirthdayRequest(
        @Body request: ChangeBirthdayRequest,
    ): Response<Void>

    @GET("/user/code")
    fun checkVerificationCode(
        @Query("email") email: String,
        @Query("code") code: String,
    ): Response<BooleanResponse>

    @GET("/user/check")
    fun checkIdDuplication(
        @Query("account-id") id: String,
    ): Response<BooleanResponse>

    @POST("/image")
    fun uploadImage(
        @Body request: UploadImageRequest,
    ): Response<UploadImageResponse>
}