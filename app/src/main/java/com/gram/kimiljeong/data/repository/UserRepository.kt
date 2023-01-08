package com.gram.kimiljeong.data.repository

import com.gram.kimiljeong.data.api.remote.AuthAPI
import com.gram.kimiljeong.data.model.remote.request.*
import com.gram.kimiljeong.data.model.remote.response.*
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val authApi: AuthAPI,
) : AuthAPI {

    override suspend fun renewToken(refreshToken: String): Response<RenewTokenResponse> {
        return authApi.renewToken(
            refreshToken = refreshToken,
        )
    }

    override suspend fun sendVerificationMail(email: String): Response<Void> {
        return authApi.sendVerificationMail(
            email = email,
        )
    }

    override suspend fun signUp(request: SignUpRequest): Response<Void> {
        return authApi.signUp(
            request = request,
        )
    }

    override suspend fun getSelfInformation(): Response<SelfInformationResponse> {
        return authApi.getSelfInformation()
    }

    override suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return authApi.login(
            request = request,
        )
    }

    override suspend fun changePassword(request: ChangePasswordRequest): Response<Void> {
        return authApi.changePassword(
            request = request,
        )
    }

    override suspend fun changeBirthdayRequest(request: ChangeBirthdayRequest): Response<Void> {
        return authApi.changeBirthdayRequest(
            request = request,
        )
    }

    override suspend fun checkVerificationCode(
        email: String,
        code: String,
    ): Response<BooleanResponse> {
        return authApi.checkVerificationCode(
            email = email,
            code = code,
        )
    }

    override suspend fun checkIdDuplication(id: String): Response<BooleanResponse> {
        return authApi.checkIdDuplication(
            id = id,
        )
    }

    override suspend fun uploadImage(request: UploadImageRequest): Response<UploadImageResponse> {
        return authApi.uploadImage(
            request = request,
        )
    }
}
