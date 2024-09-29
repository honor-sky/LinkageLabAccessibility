package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.MonthItemBinding
import java.util.Calendar
import java.util.Date


class MonthAdapter: RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {
    var calendar = Calendar.getInstance()

    inner class MonthViewHolder (val binding: MonthItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            //달 구하기
            calendar.time = Date() //현재 날짜 초기화
            calendar.set(Calendar.DAY_OF_MONTH, 1) //스크롤시 현재 월의 1일로 이동
            calendar.add(Calendar.MONTH, position) //스크롤시 포지션 만큼 달이동
            binding.title.text =
                "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월"


            val tempYear = calendar.get(Calendar.YEAR)
            val tempMonth = calendar.get(Calendar.MONTH)

            //일 구하기
            //6주 7일로 날짜를 표시
            var dayList = MutableList(6 * 7) { Date() }
            for (i in 0..5) { //주
                for (k in 0..6) { //요일
                    //각 달의 요일만큼 캘린더에 보여진다
                    //요일 표시
                    calendar.add(
                        Calendar.DAY_OF_MONTH,
                        (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k
                    )
                    dayList[i * 7 + k] = calendar.time //배열 인덱스 만큼 요일 데이터 저장
                }
                //주 표시
                calendar.add(Calendar.WEEK_OF_MONTH, 1)


                binding.monthRecycler.layoutManager = GridLayoutManager(binding.root.context, 7)
                binding.monthRecycler.adapter = DayAdapter(tempYear, tempMonth, dayList)
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
            return MonthViewHolder(MonthItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }

        override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bind()
    }


    override fun getItemCount(): Int {
        return Int.MAX_VALUE / 2
    }

    class Month(val view: View) : RecyclerView.ViewHolder(view)
}