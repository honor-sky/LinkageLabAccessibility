package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.linkagelab.databinding.FragmentCalendarBinding
import java.util.Calendar
import java.util.Date

class CalendarFrag : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    val calendar = Calendar.getInstance()
    var position = 0

    val dayAdapter =  DayAdapter(2024, 10)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        calendar.time = Date() //현재 날짜 초기화

        val current_year = calendar.get(Calendar.YEAR)
        val current_month = calendar.get(Calendar.MONTH)
        calendar.set(current_year, current_month, 1)

        binding.nextMonth.setOnClickListener {
            position += 1
            initCalendar(current_year, current_month + position, position)
        }

        binding.prevMonth.setOnClickListener {
            position -= 1
            initCalendar(current_year, current_month + position, position)
        }

        initCalendar(current_year, current_month, position)

        return root
    }

    fun initCalendar(year : Int, month : Int, position : Int) { // 몇년 후인지, 몇달 후인지 결정
        calendar.set(year, month, 1)
        //calendar.add(Calendar.DAY_OF_MONTH, position)

        binding.title.text =
            "${year}년 ${month + 1}월"

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // 해당 달의 마지막 날 구하기
        val maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // 날짜 리스트 초기화
        var dayList = MutableList<Date?>(5 * 7) { Date() }


        var dayCount = 1
        for (i in 0..4) { //주
            for (k in 0..6) { //요일
                //각 달의 요일만큼 캘린더에 보여진다
                //요일 표시
                val index = i * 7 + k
                // 첫 주의 첫 요일부터 시작하도록 조정
                if (index >= firstDayOfWeek - 1 && dayCount <= maxDayOfMonth) {
                    // 해당 날짜를 설정
                    calendar.set(Calendar.DAY_OF_MONTH, dayCount)
                    dayList[index] = calendar.time // 날짜를 리스트에 추가
                    dayCount += 1 // 다음 날짜로 이동
                } else {
                    dayList[index] = null
                }
            }
        }

        binding.monthRecycler.layoutManager = GridLayoutManager(binding.root.context, 7)

        dayAdapter.setDate(dayList)
        binding.monthRecycler.adapter = dayAdapter

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}