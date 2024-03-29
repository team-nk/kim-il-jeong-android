package team.nk.kimiljeong.data.api.remote

import retrofit2.Response
import retrofit2.http.*
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.request.CreateScheduleRequest
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse

interface ScheduleAPI {

    @GET("/schedule")
    suspend fun inquireDateScheduleList(
        @Query("date") date: String,
    ): Response<InquireScheduleListResponse>

    @GET("/schedule/{schedule-id}")
    suspend fun inquireSpecificScheduleInformation(
        @Path("schedule-id") id: Int,
    ): Response<ScheduleInformation>

    @POST("/schedule")
    suspend fun createSchedule(
        @Body request: CreateScheduleRequest,
    ): Response<Void>

    @PUT("/schedule/{schedule-id}")
    suspend fun editSchedule(
        @Path("schedule-id") scheduleId: Int,
        @Body request: CreateScheduleRequest,
    ): Response<Void>

    @DELETE("/schedule/{schedule-id}")
    suspend fun removeSchedule(
        @Path("schedule-id") scheduleId: Int,
    ): Response<Void>

    @GET("/schedule/map")
    suspend fun inquireSpecificLocationOfScheduleList(): Response<InquireScheduleListResponse>

    @GET("/schedule/list")
    suspend fun inquireEntireScheduleList(): Response<InquireScheduleListResponse>

    @GET("/schedule/choose")
    suspend fun inquireChooseScheduleList(): Response<InquireScheduleListResponse> // TODO 서버에 Response 키값 통일 요청
}
