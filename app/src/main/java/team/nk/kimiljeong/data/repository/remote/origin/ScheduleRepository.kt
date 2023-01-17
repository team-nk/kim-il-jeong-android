package team.nk.kimiljeong.data.repository.remote.origin

import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.ScheduleAPI
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse

interface ScheduleRepository : ScheduleAPI {

    override suspend fun inquireDateScheduleList(date: String): Response<InquireScheduleListResponse>

    override suspend fun createSchedule(request: ScheduleInformation): Response<Void>

    override suspend fun editSchedule(
        scheduleId: String,
        request: ScheduleInformation,
    ): Response<Void>

    override suspend fun removeSchedule(scheduleId: String): Response<Void>

    override suspend fun inquireSpecificLocationOfScheduleList(): Response<InquireScheduleListResponse>

    override suspend fun inquireEntireScheduleList(): Response<InquireScheduleListResponse>

    override suspend fun inquireChooseScheduleList(): Response<InquireScheduleListResponse>
}