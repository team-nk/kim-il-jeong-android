package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color.*
import team.nk.kimiljeong.data.extension.toColor
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.ItemScheduleBinding
import team.nk.kimiljeong.presentation.view.map.ScheduleItemClickListener
import team.nk.kimiljeong.presentation.view.post.ItemClickListener

class ScheduleAdapter(
    private val schedules: List<ScheduleInformation>,
    private val onItemClick: ScheduleItemClickListener?,
) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(schedule: ScheduleInformation) {
            with(binding) {
                tvItemCalendarTodayScheduleTitle.text = schedule.content
                tvItemCalendarTodayScheduleDate.text = schedule.startsAt?.replace("T", " ")
                indicatorItemCalendarTodaySchedule.setBackgroundResource(
                    when (schedule.color?.toColor()) {
                        RED -> R.drawable.bg_create_schedule_color_indicator_red_unchecked
                        BLUE -> R.drawable.bg_create_schedule_color_indicator_blue_unchecked
                        YELLOW -> R.drawable.bg_create_schedule_color_indicator_yellow_unchecked
                        PURPLE -> R.drawable.bg_create_schedule_color_indicator_purple_unchecked
                        GREEN -> R.drawable.bg_create_schedule_color_indicator_green_unchecked
                        ERROR -> R.drawable.img_global_temp
                        null -> R.drawable.img_global_temp
                    }
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
        holder.itemView.setOnClickListener {
            onItemClick?.onScheduleItemClick(
                scheduleId = schedules[position].scheduleId!!,
                color = schedules[position].color!!,
                content = schedules[position].content!!,
                address = schedules[position].address!!,
                startsAt = schedules[position].startsAt!!,
                endsAt = schedules[position].endsAt!!,
            )
        }
    }

    override fun getItemCount(): Int {
        return schedules.size
    }
}