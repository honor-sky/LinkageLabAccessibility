package com.example.linkagelab.presentation

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.linkagelab.databinding.FragmentCalendarBinding
import java.util.Calendar
import java.util.Date

class CalendarFrag : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val calendar = Calendar.getInstance()
        calendar.time = Date() //현재 날짜 초기화
        calendar.set(Calendar.DAY_OF_MONTH, 1) //스크롤시 현재 월의 1일로 이동

        binding.nextMonth.setOnClickListener {

        }

        binding.prevMonth.setOnClickListener {

        }

        //initCalendar()

        return root
    }

    fun initCalendar(year : Int, month : Int) {
        //binding.calendarViewCustom.adapter = MonthAdapter()

        //calendar.add(Calendar.MONTH, position) //스크롤시 포지션 만큼 달이동
        binding.title.text =
            "${year}년 ${month + 1}월"

        //일 구하기
        //6주 7일로 날짜를 표시
        var dayList = MutableList(6 * 7) { Date() }
       /* for (i in 0..5) { //주
            for (k in 0..6) { //요일
                //각 달의 요일만큼 캘린더에 보여진다
                //요일 표시
                calendar.add(
                    Calendar.DAY_OF_MONTH,
                    (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k
                )
                dayList[i * 7 + k] = calendar.time // 배열 인덱스 만큼 요일 데이터 저장
            }
            //주 표시
            calendar.add(Calendar.WEEK_OF_MONTH, 1)


            binding.monthRecycler.layoutManager = GridLayoutManager(binding.root.context, 7)
            binding.monthRecycler.adapter = DayAdapter(tempYear, tempMonth, dayList)

        }*/
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}