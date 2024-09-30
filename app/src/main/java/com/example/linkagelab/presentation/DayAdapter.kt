package com.example.linkagelab.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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

    lateinit var dayList: MutableList<Date?>
    val ROW = 5
    private var selectedDate: Date? = null // 선택된 날짜를 저장할 변수
    val today = Calendar.getInstance()

    class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    inner class DayViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Date) {

            Log.d("DayAdapter","오늘 : ${today.get(Calendar.YEAR)}, ${today.get(Calendar.MONTH)}, ${ today.get(Calendar.DAY_OF_MONTH)}")

            if(today.get(Calendar.YEAR) == item.year &&
                today.get(Calendar.MONTH) + 1 == item.month &&
                today.get(Calendar.DAY_OF_MONTH) == item.day) {
                selectedDate = item
                binding.itemDayText.setBackgroundColor(binding.itemDayText.context.getColor(R.color.kakao_yellow)) // 선택된 날짜의 배경색
            }

            //날짜 표시
            binding.itemDayText.text = item.date.toString()
            binding.itemDayText.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
                override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    val calendar = Calendar.getInstance()
                    calendar.time = item

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

                    if(selectedDate == item) {
                        if(today.get(Calendar.YEAR) == item.year &&
                            today.get(Calendar.MONTH) == item.month &&
                            today.get(Calendar.DAY_OF_MONTH) == item.day) {
                            info.text = "오늘, 선택됨, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                        } else {
                            info.text = "선택됨, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                        }

                    } else {
                        if(today.get(Calendar.YEAR) == item.year &&
                            today.get(Calendar.MONTH) == item.month &&
                            today.get(Calendar.DAY_OF_MONTH) == item.day) {
                            info.text = "오늘, ${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                        } else {
                            info.text = "${year}년 ${month}월 ${day}일 ${dayOfWeek}"
                        }
                    }

                   // 접근성 정보 설정
                   /* binding.itemDayText.announceForAccessibility("${month}월 ${day}일 ${dayOfWeek}")
                    binding.itemDayText.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)*/

                }

                override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                    return when (action) {
                        AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {
                            //binding.itemDayText.announceForAccessibility("${month}월 ${day}일 ${dayOfWeek}")
                            //binding.itemDayText.contentDescription =  "${tempMonth}월 ${item.date}일"
                            //binding.itemDayText.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                            super.performAccessibilityAction(host, action, args)
                        }

                        else -> super.performAccessibilityAction(host, action, args)
                    }
                }
            })

            if(tempMonth != item.month) {
                binding.itemDayText.alpha = 0.4f
            }

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

            // 선택된 날짜 강조 표시
            if (selectedDate == item) {
                binding.itemDayText.setBackgroundColor(binding.itemDayText.context.getColor(R.color.kakao_yellow)) // 선택된 날짜의 배경색
            } else {
                binding.itemDayText.setBackgroundColor(Color.TRANSPARENT) // 기본 배경색
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


    override fun getItemCount(): Int {
        return ROW*7
    }
}