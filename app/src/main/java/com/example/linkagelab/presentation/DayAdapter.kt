package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.DayItemBinding
import com.example.linkagelab.databinding.HorizontalItemBinding
import com.example.linkagelab.databinding.MonthItemBinding
import java.util.Calendar
import java.util.Date

class DayAdapter(val tempYear:Int, val tempMonth:Int, val dayList: MutableList<Date>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    val ROW =6
    class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    inner class DayViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Date) {

            //날짜 표시
            binding.itemDayText.text = item.date.toString()
            binding.itemDayText.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
                override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(host, info)

                    info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS)
                    //info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)
                }

                override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                    return when (action) {
                        AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {
                            binding.itemDayText.contentDescription =  "${tempMonth}월 ${item.date}일"
                            binding.itemDayText.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                            false
                        }

                        else -> super.performAccessibilityAction(host, action, args)
                    }
                }
            })

            if(tempMonth != item.month) {
                binding.itemDayText.alpha = 0.4f
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(dayList[position])

    }


    override fun getItemCount(): Int {
        return ROW*7
    }
}