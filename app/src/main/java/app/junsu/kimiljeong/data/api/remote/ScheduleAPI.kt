package app.junsu.kimiljeong.data.api.remote

import app.junsu.kimiljeong.data.model.remote.common.ScheduleInformation
import app.junsu.kimiljeong.data.model.remote.request.SpecificLocationOfScheduleListRequest
import app.junsu.kimiljeong.data.model.remote.response.InquireScheduleListResponse
import retrofit2.Response
import retrofit2.http.*

interface ScheduleAPI {

    @GET("/schedule")
    fun inquireDateScheduleList(
        @Query("date") date: String,
    ): Response<InquireScheduleListResponse>

    @POST("/schedule")
    fun createSchedule(
        @Body request: ScheduleInformation,
    ): Response<Void>

    @PUT("/schedule/{schedule-id}")
    fun editSchedule(
        @Path("schedule-id") scheduleId: String,
        @Body request: ScheduleInformation,
    ): Response<Void>

    @DELETE("/schedule/{schedule-id")
    fun removeSchedule(
        @Path("schedule-id") scheduleId: String,
    ): Response<Void>

    @GET("/schedule/map")
    fun inquireSpecificLocationOfScheduleList(
        @Body request: SpecificLocationOfScheduleListRequest,
    ): Response<InquireScheduleListResponse>

    @GET("/schedule/list")
    fun inquireEntireScheduleList(): Response<InquireScheduleListResponse>

    @GET("/schedule/choose")
    fun inquireChooseScheduleList(): Response<InquireScheduleListResponse> // TODO 서버에 Response 키값 통일 요청
}
