package com.example.linkagelab.presentation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.CalendarView

class CustomCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null  //, defStyleAttr: Int = 0
) : CalendarView(context, attrs) {

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(info)

        // 날짜 선택이 포커스 되었을 때 접근성 메시지 설정
        val selectedDate = getSelectedDate()
        info.contentDescription = "선택된 날짜는 ${selectedDate}입니다."
    }

    private fun getSelectedDate(): String {
        // 선택된 날짜를 반환하는 로직 구현
        // 예시: "2024년 9월 29일"
        return "2024년 9월 29일" // 예시, 실제로는 현재 선택된 날짜로 변경해야 함
    }

    override fun onPopulateAccessibilityEvent(event: AccessibilityEvent) {
        super.onPopulateAccessibilityEvent(event)

        if(event.eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
            Log.d("calendarView", "초점 : ${getSelectedDate()}")
        }

        // 포커스가 이동할 때의 접근성 메시지 설정
        event.text.add("현재 선택된 날짜는 ${getSelectedDate()}입니다.")
        sendAccessibilityEventUnchecked(event)
    }
}