package team.nk.kimiljeong.data.repository.remote.implement

import retrofit2.Response
import team.nk.kimiljeong.data.api.remote.ScheduleAPI
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse
import team.nk.kimiljeong.data.repository.remote.origin.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleAPI: ScheduleAPI,
) : ScheduleRepository {
    override suspend fun inquireDateScheduleList(date: String): Response<InquireScheduleListResponse> {
        return scheduleAPI.inquireDateScheduleList(
            date = date,
        )
    }

    override suspend fun createSchedule(request: ScheduleInformation): Response<Void> {
        return scheduleAPI.createSchedule(
            request = request,
        )
    }

    override suspend fun editSchedule(
        scheduleId: String,
        request: ScheduleInformation,
    ): Response<Void> {
        return scheduleAPI.editSchedule(
            scheduleId = scheduleId,
            request = request,
        )
    }

    override suspend fun removeSchedule(scheduleId: Int): Response<Void> {
        return scheduleAPI.removeSchedule(
            scheduleId = scheduleId,
        )
    }

    override suspend fun inquireSpecificLocationOfScheduleList(): Response<InquireScheduleListResponse> {
        return scheduleAPI.inquireSpecificLocationOfScheduleList()
    }

    override suspend fun inquireEntireScheduleList(): Response<InquireScheduleListResponse> {
        return scheduleAPI.inquireChooseScheduleList()
    }

    override suspend fun inquireChooseScheduleList(): Response<InquireScheduleListResponse> {
        return scheduleAPI.inquireChooseScheduleList()
    }
}