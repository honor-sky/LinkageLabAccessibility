package com.example.linkagelab.presentation.picker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.DayItemBinding
import java.util.Calendar
import java.util.Date

class DayAdapter(val tempYear:Int, val tempMonth:Int ) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    // 날짜 리스트
    lateinit var dayList: MutableList<Date?>
    lateinit var holidayList : MutableList<String>

    val ROW = 5
    val today = Calendar.getInstance()
    private var selectedDate: Date? =  today.time

    class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    inner class DayViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Date) {

            val calendar = Calendar.getInstance()
            calendar.time = item

            val selected_calendar = Calendar.getInstance()
            selected_calendar.time = selectedDate

            val dayStrigForHoliday = mutableListOf(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)).joinToString("-")


            // 휴일 표시 강조
            if( holidayList.any { it == dayStrigForHoliday}) {
                binding.holidaySign.visibility = View.VISIBLE
            } else {
                binding.holidaySign.visibility = View.GONE
            }


            // 선택된 날짜 강조 표시
            if(selected_calendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                selected_calendar.get(Calendar.MONTH) + 1 == calendar.get(Calendar.MONTH) + 1&&
                selected_calendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {

                selectedDate = item

                binding.itemDayText.setBackgroundColor(binding.itemDayText.context.getColor(R.color.kakao_yellow))
            } else {
                binding.itemDayText.setBackgroundColor(Color.WHITE)
            }


            //날짜 표시
            binding.itemDayText.text = item.date.toString()

            binding.itemDayText.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
                override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(host, info)

                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH) + 1 // 0부터 시작하므로 +1 필요
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val dayOfWeek = when(calendar.get(Calendar.DAY_OF_WEEK)) {
                        2 -> "월요일"
                        3 -> "화요일"
                        4 -> "수요일"
                        5 -> "목요일"
                        6 -> "금요일"
                        7 -> "토요일"
                        1 -> "일요일"
                        else -> {}
                    }


                    if(selectedDate == item) { // 선택된 날
                        // 오늘
                        if(today.get(Calendar.YEAR) == year &&
                            today.get(Calendar.MONTH) + 1 == month &&
                            today.get(Calendar.DAY_OF_MONTH) == day) {
                            // 공휴일인 경우
                            if( holidayList.any { it == dayStrigForHoliday}) {
                                info.text = "오늘, 선택됨, 공휴일, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            } else {
                                info.text = "오늘, 선택됨, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            }
                        } else {
                            // 공휴일인 경우
                            if( holidayList.any { it == dayStrigForHoliday}) {
                                info.text = "선택됨, 공휴일, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            } else {
                                info.text = "선택됨, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            }
                        }

                    } else { // 선택 안 된 날
                        // 오늘
                        if(today.get(Calendar.YEAR) == year &&
                            today.get(Calendar.MONTH) + 1 == month &&
                            today.get(Calendar.DAY_OF_MONTH) == day) {

                            if( holidayList.any { it == dayStrigForHoliday}) {
                                info.text = "오늘, 공휴일, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            } else {
                                info.text = "오늘, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            }

                        } else {
                            if( holidayList.any { it == dayStrigForHoliday}) {
                                info.text = "공휴일, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            } else {
                                info.text = "${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                            }
                        }
                    }

                }

            })


            binding.root.setOnClickListener {
                // 이전 선택된 날짜의 상태를 초기화
                selectedDate?.let { prevSelectedDate ->
                    // 선택된 날짜의 투명도 원래대로 복원
                    notifyItemChanged(dayList.indexOf(prevSelectedDate))
                }

                // 현재 클릭된 날짜를 선택된 날짜로 설정
                selectedDate = item

                // 선택된 날짜의 투명도 변경 (강조)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        //holder.bind(dayList[position])
        val item = dayList[position]
        if (item != null) {
            holder.bind(item)
        } else {
            // 빈 공간일 경우, UI에서 해당 아이템을 숨기거나 설정
            holder.binding.itemDayText.text = "" // 또는 기본 텍스트
            holder.binding.itemDayText.alpha = 0f // 투명하게 하여 보이지 않게 함
        }

    }


    fun setDate(dayList : MutableList<Date?>) {
        this.dayList = dayList
        //notifyDataSetChanged()
    }

    fun setHoliday(holidayList: MutableList<String>) {
        this.holidayList = holidayList
    }


    override fun getItemCount(): Int {
        return ROW*7
    }
}