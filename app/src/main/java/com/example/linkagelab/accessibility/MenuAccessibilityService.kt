package com.example.linkagelab.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MenuAccessibilityService :  AccessibilityService() { //:
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.d("MyAccessibilityService", "접근성 감지")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    override fun onGesture(gestureId: Int): Boolean {
        // 제스처가 인식되었을 때 처리
        return when (gestureId) {
            GESTURE_SWIPE_UP -> {
                // 스와이프 왼쪽 제스처 커스텀 동작
                Log.d("MyAccessibilityService", "위로 스와이프")
                true
            }
            GESTURE_SWIPE_DOWN -> {
                // 스와이프 오른쪽 제스처 커스텀 동작
                Log.d("MyAccessibilityService", "아래로 스와이프")
                true
            }
            else -> super.onGesture(gestureId)
        }
    }


}