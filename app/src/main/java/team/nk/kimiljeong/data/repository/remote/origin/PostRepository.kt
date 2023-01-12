package team.nk.kimiljeong.data.repository.remote.origin

import retrofit2.Response
import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.model.remote.response.InquireBirthdayListResponse
import team.nk.kimiljeong.data.model.remote.response.InquireCommentListResponse
import team.nk.kimiljeong.data.model.remote.response.InquirePostListResponse

interface PostRepository {

    suspend fun inquirePostList(): Response<InquirePostListResponse>

    suspend fun createPost(
        request: CreatePostRequest,
    ): Response<Void>

    suspend fun createComment(
        postId: Int,
    ): Response<Void>

    suspend fun inquireBirthdayList(): Response<InquireBirthdayListResponse>

    suspend fun inquireCommentList(
        postId: Int,
    ): Response<InquireCommentListResponse>
}
