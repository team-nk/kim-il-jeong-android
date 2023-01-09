package team.nk.kimiljeong.data.api.remote

import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.model.remote.response.InquireBirthdayListResponse
import team.nk.kimiljeong.data.model.remote.response.InquireCommentListResponse
import team.nk.kimiljeong.data.model.remote.response.InquirePostListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostAPI {

    @GET("/post")
    suspend fun inquirePostList(): Response<InquirePostListResponse>

    @POST("/post")
    suspend fun createPost(
        @Body request: CreatePostRequest,
    ): Response<Void>

    @GET("/post/birthday")
    suspend fun inquireBirthdayList(): Response<InquireBirthdayListResponse>

    @GET("/comment/{post-id}")
    suspend fun inquireCommentList(
        @Path("post-id") postId: Int,
    ): Response<InquireCommentListResponse>

    @POST("/comment/{post-id}")
    suspend fun createComment(
        @Path("post-id") postId: Int,
    ): Response<Void>
}
