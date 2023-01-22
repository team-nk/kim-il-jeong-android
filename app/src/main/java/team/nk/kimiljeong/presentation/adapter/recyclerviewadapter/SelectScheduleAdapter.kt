package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color
import team.nk.kimiljeong.data.extension.toColor
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.ItemScheduleBinding

// TODO ScheduleAdapter랑 통합
class SelectScheduleAdapter(
    private val schedules: List<ScheduleInformation>,
    private val onItemClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<SelectScheduleAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(schedule: ScheduleInformation) {
            with(binding) {
                tvItemCalendarTodayScheduleTitle.text = schedule.content
                tvItemCalendarTodayScheduleDate.text = setTime(
                    time = schedule.startsAt,
                    context = binding.root.context,
                )
                indicatorItemCalendarTodaySchedule.setBackgroundResource(
                    when (schedule.color?.toColor()) {
                        Color.RED -> R.drawable.bg_create_schedule_color_indicator_red_unchecked
                        Color.BLUE -> R.drawable.bg_create_schedule_color_indicator_blue_unchecked
                        Color.YELLOW -> R.drawable.bg_create_schedule_color_indicator_yellow_unchecked
                        Color.PURPLE -> R.drawable.bg_create_schedule_color_indicator_purple_unchecked
                        Color.GREEN -> R.drawable.bg_create_schedule_color_indicator_green_unchecked
                        Color.ERROR -> R.drawable.img_global_temp
                        null -> R.drawable.img_global_temp
                    }
                )
                root.setOnClickListener {
                    onItemClick(schedule.scheduleId!!)
                }
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

    override fun onBindViewHolder(holder: SelectScheduleAdapter.ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    private fun setTime(
        time: String?,
        context: Context?,
    ): String =
        StringBuilder().run {
            time?.replace("T", " ")?.split('.')?.get(0) ?: context!!.getString(R.string.allDay)
        }
}