package com.example.linkagelab.presentation

import android.content.Context.ACCESSIBILITY_SERVICE
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.example.linkagelab.R
import com.example.linkagelab.databinding.FragmentDatePickerBinding


class DatePickerFrag : Fragment() {

    private var _binding: FragmentDatePickerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDatePickerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //setPickerChild(binding.datePickerCustom)
        //customizeDatePicker(binding.datePickerCustom)

        //setAccessibility()


        binding.yearPicker.minValue = 1900
        binding.yearPicker.maxValue = 2100
        binding.yearPicker.displayedValues = getDisplayValues(1900, 2100, "년")
        binding.yearPicker.value = 2024
        binding.yearPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            val customMessage = "${binding.yearPicker.value}년 ${binding.monthPicker.value}월 ${binding.dayPicker.value}일로 설정"
            binding.yearPicker.announceForAccessibility(customMessage)
        }
        

        binding.monthPicker.minValue = 1
        binding.monthPicker.maxValue = 12
        binding.monthPicker.displayedValues = getDisplayValues(1, 12, "월")
        binding.monthPicker.value = 10
        binding.monthPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            val customMessage = "${binding.yearPicker.value}년 ${binding.monthPicker.value}월 ${binding.dayPicker.value}일로 설정"
            binding.monthPicker.announceForAccessibility(customMessage)
        }

        binding.dayPicker.minValue = 1
        binding.dayPicker.maxValue = 31
        binding.dayPicker.displayedValues = getDisplayValues(1, 31, "일")
        binding.dayPicker.value = 1
        binding.dayPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            val customMessage = "${binding.yearPicker.value}년 ${binding.monthPicker.value}월 ${binding.dayPicker.value}일로 설정"
            binding.dayPicker.announceForAccessibility(customMessage)
        }

        return root
    }


    fun customizeDatePicker(datePicker: DatePicker) {
        try {
            // DatePicker 내부의 NumberPicker(년, 월, 일)를 찾음
            val dayPickerId = Resources.getSystem().getIdentifier("day", "id", "android")
            val monthPickerId = Resources.getSystem().getIdentifier("month", "id", "android")
            val yearPickerId = Resources.getSystem().getIdentifier("year", "id", "android")

            val dayPicker = datePicker.findViewById<NumberPicker>(dayPickerId)
            val monthPicker = datePicker.findViewById<NumberPicker>(monthPickerId)
            val yearPicker = datePicker.findViewById<NumberPicker>(yearPickerId)

            datePicker.init(2024, 10, 1) { _, year, monthOfYear, dayOfMonth ->
                // 사용자 정의 접근성 메시지 출력
                val customMessage = "${year}년 ${monthOfYear + 1}월 ${dayOfMonth}일로 설정."
                datePicker.announceForAccessibility(customMessage)
            }

            // 숫자 옆에 년, 월, 일 글씨 추가
            dayPicker?.let {
                dayPicker.minValue = 1
                dayPicker.maxValue = 31
                dayPicker.displayedValues = getDisplayValues(1, 31, "일")
                dayPicker.value = 1
            }

            monthPicker?.let {
                monthPicker.minValue = 1
                monthPicker.maxValue = 12
                monthPicker.displayedValues = getDisplayValues(1, 12, "월")
                monthPicker.value = 10
            }

            yearPicker?.let {
                yearPicker.minValue = 1900
                yearPicker.maxValue = 2100
                yearPicker.displayedValues = getDisplayValues(1900, 2100, "년")
                yearPicker.value = 2024
            }

            disableDefaultAccessibility(dayPicker)
            disableDefaultAccessibility(monthPicker)
            disableDefaultAccessibility(yearPicker)



        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disableDefaultAccessibility(numberPicker: NumberPicker?) {
        numberPicker?.let {
            it.accessibilityDelegate = object : View.AccessibilityDelegate() {
                // 접근성 이벤트 무효화
                override fun sendAccessibilityEvent(host: View, eventType: Int) {
                    // 기본 접근성 이벤트를 전송하지 않음 (음성 출력 차단)
                }

                override fun onInitializeAccessibilityNodeInfo(
                    host: View,
                    info: AccessibilityNodeInfo
                ) {
                    // 기본 접근성 정보 초기화하지 않음
                }

                override fun onInitializeAccessibilityEvent(host: View, event: AccessibilityEvent) {
                    // 기본 접근성 이벤트 초기화하지 않음
                }
            }
        }
    }



    private fun getDisplayValues(start: Int, end: Int, suffix: String): Array<String> {
        val displayValues = mutableListOf<String>()

        for (value in start..end) {
            displayValues.add("${value}${suffix}")
        }
        return displayValues.toTypedArray()
    }

    fun setPickerChild(viewGroup : ViewGroup) {
        for (i in 0 until  viewGroup.getChildCount()) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                setPickerChild(child)
            } else {
                if(child is EditText) {
                    child.isClickable = false
                    child.focusable = View.NOT_FOCUSABLE

                    child.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}