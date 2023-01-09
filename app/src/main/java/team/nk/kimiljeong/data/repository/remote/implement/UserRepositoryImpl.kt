package team.nk.kimiljeong.data.repository.remote.implement

import com.gram.kimiljeong.data.model.remote.response.SelfInformationResponse
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.AuthAPI
import team.nk.kimiljeong.data.common.preferences.AuthPreferences
import team.nk.kimiljeong.data.model.remote.request.*
import team.nk.kimiljeong.data.model.remote.response.BooleanResponse
import team.nk.kimiljeong.data.model.remote.response.LoginResponse
import team.nk.kimiljeong.data.model.remote.response.RenewTokenResponse
import team.nk.kimiljeong.data.model.remote.response.UploadImageResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authApi: AuthAPI,
    private val authPreferences: AuthPreferences,
) : UserRepository {

    override suspend fun renewToken(refreshToken: String): Response<RenewTokenResponse> {
        return authApi.renewToken(
            refreshToken = refreshToken,
        )
    }

    override suspend fun sendVerificationMail(
        email: String,
    ): Response<Void> {
        return authApi.sendVerificationMail(
            email = email,
        )
    }

    override suspend fun signUp(
        request: SignUpRequest,
    ): Response<Void> {
        return authApi.signUp(
            request = request,
        )
    }

    override suspend fun getSelfInformation(): Response<SelfInformationResponse> {
        return authApi.getSelfInformation()
    }

    override suspend fun login(
        request: LoginRequest,
    ): Response<LoginResponse> {
        return authApi.login(
            request = request,
        )
    }

    override suspend fun changePassword(
        request: ChangePasswordRequest,
    ): Response<Void> {
        return authApi.changePassword(
            request = request,
        )
    }

    override suspend fun changeBirthdayRequest(
        request: ChangeBirthdayRequest,
    ): Response<Void> {
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

    override suspend fun checkIdDuplication(
        id: String,
    ): Response<BooleanResponse> {
        return authApi.checkIdDuplication(
            id = id,
        )
    }

    override suspend fun uploadImage(
        request: UploadImageRequest,
    ): Response<UploadImageResponse> {
        return authApi.uploadImage(
            request = request,
        )
    }

    override suspend fun saveAccessToken(token: String) {
        authPreferences.saveAccessToken(
            token,
        )
    }

    override suspend fun saveRefreshToken(token: String) {
        authPreferences.saveRefreshToken(
            token,
        )
    }

    override suspend fun fetchTokens(): Pair<String, String> {
        return authPreferences.fetchTokens()
    }

    override suspend fun saveTokens(refreshToken: String, accessToken: String) {
        authPreferences.saveTokens(
            refreshToken = refreshToken,
            accessToken = accessToken,
        )
    }
}
