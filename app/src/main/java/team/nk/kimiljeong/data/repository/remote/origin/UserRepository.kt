package team.nk.kimiljeong.data.repository.remote.origin

import com.gram.kimiljeong.data.model.remote.response.SelfInformationResponse
import okhttp3.MultipartBody
import retrofit2.Response
import team.nk.kimiljeong.data.model.remote.request.*
import team.nk.kimiljeong.data.model.remote.response.BooleanResponse
import team.nk.kimiljeong.data.model.remote.response.LoginResponse
import team.nk.kimiljeong.data.model.remote.response.RenewTokenResponse
import team.nk.kimiljeong.data.model.remote.response.UploadImageResponse

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

    suspend fun checkLoggedIn(): Boolean

    suspend fun setToLoggedIn()

    suspend fun setToLoggedOut()

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
        image: MultipartBody.Part,
    ): Response<UploadImageResponse>

    suspend fun saveAccessToken(
        token: String,
    )

    suspend fun saveRefreshToken(
        token: String,
    )

    suspend fun fetchTokens(): Pair<String, String>

    suspend fun saveTokens(
        refreshToken: String,
        accessToken: String,
    )

    suspend fun changeUserInformation(
        request: ChangeUserInformationRequest,
    ): Response<Void>
}
