package com.example.linkagelab.presentation

import android.content.Context.ACCESSIBILITY_SERVICE
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.linkagelab.databinding.FragmentDatePickerBinding


class DatePickerFrag : Fragment() {

    private var _binding: FragmentDatePickerBinding? = null
    private val binding get() = _binding!!

    private lateinit var accessibilityManager: AccessibilityManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDatePickerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setPickerChild(binding.datePickerCustom)
        // AccessibilityManager 초기화
        accessibilityManager = requireContext().getSystemService(AccessibilityManager::class.java)

        // 접근성 이벤트 리스너 설정
        //setupAccessibilityEventListener()

        return root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setupAccessibilityEventListener() {
        Log.d("DatePickerFrag","메서드 실행")
        // 접근성 서비스 활성화 여부 확인
        if (accessibilityManager.isEnabled) {
            // 접근성 이벤트 리스너 등록
            Log.d("DatePickerFrag","accessibilityManager isEnabled")
            accessibilityManager.addAccessibilityServicesStateChangeListener {
                Log.d("DatePickerFrag","Service isEnabled")
            }
            //setAccessibilityDelegate(binding)

            /*  accessibilityManager.addAccessibilityStateChangeListener { isEnabled ->
          if (isEnabled) {
                  Log.d("DatePickerFrag","State isEnabled")
                  // 탐색 중 발생하는 AccessibilityEvent 처리
                  requireActivity().window.decorView.accessibilityDelegate = object : View.AccessibilityDelegate() {
                      override fun sendAccessibilityEvent(host: View, eventType: Int) {
                          super.sendAccessibilityEvent(host, eventType)

                          if (eventType == AccessibilityEvent.TYPE_VIEW_FOCUSED || eventType == AccessibilityEvent.TYPE_VIEW_HOVER_ENTER) {
                              // 초점이 맞춰진 뷰 정보 확인
                              host?.let {
                                  val viewType = it.javaClass.simpleName
                                  Log.d("DatePickerFrag","${viewType}")
                                  Toast.makeText(requireContext(), "현재 탐색된 뷰: $viewType", Toast.LENGTH_SHORT).show()
                              }
                          }
                      }
                  }
              }
          }*/

        }
    }

    private fun setAccessibilityDelegate(view: View) {
        view.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun sendAccessibilityEvent(host: View, eventType: Int) {
                super.sendAccessibilityEvent(host, eventType)

                if (eventType == AccessibilityEvent.TYPE_VIEW_FOCUSED || eventType == AccessibilityEvent.TYPE_VIEW_HOVER_ENTER) {
                    // 초점이 맞춰진 뷰 정보 확인
                    host.let {
                        val viewType = it.javaClass.simpleName
                        Log.d("DatePickerFrag", "현재 탐색된 뷰: $viewType")
                        Toast.makeText(requireContext(), "현재 탐색된 뷰: $viewType", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    fun setPickerChild(viewGroup : ViewGroup) { //viewGroup : ViewGroup
        for (i in 0 until viewGroup.getChildCount()) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                // 하위 ViewGroup도 재귀적으로 탐색
                setPickerChild(child)
            } else {
                // 개별 뷰에 접근, 특정 뷰인지 확인하여 리스너 추가
                if (child is EditText) {
                    Log.d("DatePicker","EditText")
                    val editText = child
                    //addChildrenForAccessibility

                    editText.setOnFocusChangeListener { v, hasFocus ->
                        v.contentDescription =  "노답"
                        v.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                    }

                }
            }
        }


    }
    




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}