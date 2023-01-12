package team.nk.kimiljeong.data.repository.remote.implement

import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.PostAPI
import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.model.remote.response.InquireBirthdayListResponse
import team.nk.kimiljeong.data.model.remote.response.InquireCommentListResponse
import team.nk.kimiljeong.data.model.remote.response.InquirePostListResponse
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostAPI,
) : PostRepository {

    override suspend fun inquirePostList(): Response<InquirePostListResponse> {
        return postApi.inquirePostList()
    }

    override suspend fun createPost(request: CreatePostRequest): Response<Void> {
        return postApi.createPost(request)
    }

    override suspend fun createComment(postId: Int): Response<Void> {
        return postApi.createComment(postId)
    }

    override suspend fun inquireBirthdayList(): Response<InquireBirthdayListResponse> {
        return postApi.inquireBirthdayList()
    }

    override suspend fun inquireCommentList(postId: Int): Response<InquireCommentListResponse> {
        return postApi.inquireCommentList(postId)
    }
}
