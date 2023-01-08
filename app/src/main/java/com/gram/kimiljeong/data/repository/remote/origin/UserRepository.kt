package com.gram.kimiljeong.data.repository.remote.origin

import com.gram.kimiljeong.data.model.remote.request.*
import com.gram.kimiljeong.data.model.remote.response.*
import retrofit2.Response

interface UserRepository {

    suspend fun renewToken(
        refreshToken: String,
    ): Response<RenewTokenResponse>

    suspend fun sendVerificationMail(
        email: String,
    ): Response<Void>

    suspend fun signUp(
        request: SignUpRequest,
    ): Response<Void>

    suspend fun getSelfInformation(): Response<SelfInformationResponse>

    suspend fun login(
        request: LoginRequest,
    ): Response<LoginResponse>

    suspend fun changePassword(
        request: ChangePasswordRequest,
    ): Response<Void>

    suspend fun changeBirthdayRequest(
        request: ChangeBirthdayRequest,
    ): Response<Void>

    suspend fun checkVerificationCode(
        email: String,
        code: String,
    ): Response<BooleanResponse>

    suspend fun checkIdDuplication(
        id: String,
    ): Response<BooleanResponse>

    suspend fun uploadImage(
        request: UploadImageRequest,
    ): Response<UploadImageResponse>
}