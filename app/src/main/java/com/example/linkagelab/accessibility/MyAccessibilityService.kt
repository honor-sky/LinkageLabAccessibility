package com.example.linkagelab.accessibility

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.annotation.RequiresApi

class MyAccessibilityService :  AccessibilityService() {


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        when (event!!.eventType) {
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED -> {
                //Log.d("MyAccessibilityService", "className : ${event!!.source}")
                val source = event.source

                val viewId = source!!.viewIdResourceName // 뷰의 ID
                val className = source.className // 컴포넌트의 클래스 이름
                val contentDescription = source.contentDescription // 접근성 설명

                // 로그로 출력
                Log.d("AccessibilityService", "View ID: $viewId")
                Log.d("AccessibilityService", "Class Name: $className")
                Log.d("AccessibilityService", "Content Description: $contentDescription")

            }
        }




    }

    override fun onInterrupt() {
        Log.d("MyAccessibilityService", "Accessibility Service Interrupted")
    }
    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("MyAccessibilityService", "Accessibility Service Connected")
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