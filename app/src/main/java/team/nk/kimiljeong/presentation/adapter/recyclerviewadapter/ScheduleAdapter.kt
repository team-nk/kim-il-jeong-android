package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color.*
import team.nk.kimiljeong.data.extension.toColor
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.ItemScheduleBinding
import team.nk.kimiljeong.presentation.util.parseColorToResource

class ScheduleAdapter(private val schedules: List<ScheduleInformation>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(schedule: ScheduleInformation) {
            with(binding) {
                tvItemCalendarTodayScheduleTitle.text = schedule.content
                tvItemCalendarTodayScheduleDate.text = schedule.startsAt ?: "Something Wrong"
                indicatorItemCalendarTodaySchedule.setBackgroundResource(
                    parseColorToResource(schedule.color)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemScheduleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    override fun getItemCount(): Int {
        return schedules.size
    }
}