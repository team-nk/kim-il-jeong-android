package team.nk.kimiljeong.data.repository.remote.origin

import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.PostAPI
import team.nk.kimiljeong.data.model.remote.request.CreateCommentRequest
import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.model.remote.response.InquireBirthdayListResponse
import team.nk.kimiljeong.data.model.remote.response.InquireCommentListResponse
import team.nk.kimiljeong.data.model.remote.response.InquirePostListResponse

interface PostRepository : PostAPI {

    override suspend fun inquirePostList(): Response<InquirePostListResponse>

    override suspend fun createPost(
        request: CreatePostRequest,
    ): Response<Void>

    override suspend fun createComment(
        postId: Int,
        request: CreateCommentRequest,
    ): Response<Void>

    override suspend fun inquireBirthdayList(): Response<InquireBirthdayListResponse>

    override suspend fun inquireCommentList(
        postId: Int,
    ): Response<InquireCommentListResponse>
}
