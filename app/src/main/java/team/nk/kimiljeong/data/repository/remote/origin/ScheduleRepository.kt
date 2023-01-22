package team.nk.kimiljeong.data.repository.remote.origin

import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.ScheduleAPI
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.request.CreateScheduleRequest
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse

interface ScheduleRepository : ScheduleAPI {

    override suspend fun inquireSpecificScheduleInformation(id: Int): Response<ScheduleInformation>

    override suspend fun inquireDateScheduleList(date: String): Response<InquireScheduleListResponse>

    override suspend fun createSchedule(request: CreateScheduleRequest): Response<Void>

    override suspend fun editSchedule(
        scheduleId: Int,
        request: CreateScheduleRequest,
    ): Response<Void>

    override suspend fun removeSchedule(scheduleId: Int): Response<Void>

    override suspend fun inquireSpecificLocationOfScheduleList(): Response<InquireScheduleListResponse>

    override suspend fun inquireEntireScheduleList(): Response<InquireScheduleListResponse>

    override suspend fun inquireChooseScheduleList(): Response<InquireScheduleListResponse>
}