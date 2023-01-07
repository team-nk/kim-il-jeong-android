package app.junsu.kimiljeong.data.api.remote

import app.junsu.kimiljeong.data.model.remote.request.CreatePostRequest
import app.junsu.kimiljeong.data.model.remote.response.InquireBirthdayListResponse
import app.junsu.kimiljeong.data.model.remote.response.InquireCommentListResponse
import app.junsu.kimiljeong.data.model.remote.response.InquirePostListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostAPI {

    @GET("/post")
    fun inquirePostList(): Response<InquirePostListResponse>

    @POST("/post")
    fun createPost(
        @Body createPostRequest: CreatePostRequest,
    ): Response<Void>

    @GET("/post/birthday")
    fun inquireBirthdayList(): Response<InquireBirthdayListResponse>

    @GET("/comment/{post-id}")
    fun inquireCommentList(
        @Path("post-id") postId: Int,
    ): Response<InquireCommentListResponse>

    @POST("/comment/{post-id}")
    fun createComment(
        @Path("post-id") postId: Int,
    ): Response<Void>
}
