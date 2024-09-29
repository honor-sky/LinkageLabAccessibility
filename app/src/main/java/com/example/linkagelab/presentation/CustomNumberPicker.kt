package com.example.linkagelab.presentation

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
import androidx.core.view.allViews
import com.example.linkagelab.R

class CustomNumberPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NumberPicker(context, attrs) {

   //lateinit var accessibilityManager : AccessibilityManager
   // private val accessibilityNodeProvider = CustomAccessibilityNodeProvider()

    init {
        this.minValue = 1
        this.maxValue = 100

        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES

    }


    override fun dispatchHoverEvent(event: MotionEvent): Boolean {
        // Hover 상태일 때 음성 피드백 추가
        if (event.action == MotionEvent.ACTION_HOVER_MOVE) {
            // 현재 포커스된 값에 따라 음성 안내 추가
            announceForAccessibility("현재 값은 ${value}입니다.")
        }
        return super.dispatchHoverEvent(event)
    }








    /* override fun dispatchHoverEvent(event: MotionEvent?): Boolean {

         val result = super.dispatchHoverEvent(event)

         // 접근성 초점이 갈 때의 액션 감지
         if (accessibilityManager.isEnabled && event != null) {
             // TalkBack 탐색 시 ACTION_HOVER_ENTER 이벤트가 발생
             if (event.action == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED ) {
                 // 접근성 이벤트 생성
                 val accessibilityEvent = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT)

                 accessibilityEvent.text.clear()
                 val newContentDescription = "${accessibilityEvent.text} 년"
                 accessibilityEvent.text.add(newContentDescription)


                 // 이벤트 전송
                 //sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                 sendAccessibilityEventUnchecked(accessibilityEvent)
             }
         }

         return result
     }*/

 /*   override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    }
*/
   /* override fun getAccessibilityNodeProvider(): AccessibilityNodeProvider? {
        return nodeProvider
    }

    inner class MyCustomAccessibilityNodeProvider : AccessibilityNodeProvider() {

        // 가상 뷰 정보 제공
        override fun createAccessibilityNodeInfo(virtualViewId: Int): AccessibilityNodeInfo? {
            val info = AccessibilityNodeInfo.obtain()

            if (virtualViewId == 1) {
                info.contentDescription = "Increment Button"
                info.setBoundsInScreen(Rect(0, 0, 100, 100))
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)
            } else if (virtualViewId == 2) {
                info.contentDescription = "Decrement Button"
                info.setBoundsInScreen(Rect(100, 0, 200, 100))
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)
            }
            return info
        }

        // 가상 뷰에 대한 클릭 액션
        override fun performAction(virtualViewId: Int, action: Int, arguments: Bundle?): Boolean {
            when (virtualViewId) {
                1 -> {
                    // Increment action
                    Log.d("NumberPicker","Increment")
                    return true
                }
                2 -> {
                    // Decrement action
                    Log.d("NumberPicker","Decrement")
                    return true
                }
            }
            return false
        }
    }*/



    override fun setValue(value: Int) {
        super.setValue(value)
        val currentValueDescription = "현재 값은 $value 입니다"
        contentDescription = currentValueDescription
        Log.d("NumberPicker", "${currentValueDescription}")

        sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
    }



    override fun onPopulateAccessibilityEvent(event: AccessibilityEvent) {
        super.onPopulateAccessibilityEvent(event)
        event.contentDescription = contentDescription
    }


}