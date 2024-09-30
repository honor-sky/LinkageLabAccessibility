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

        setPickerChild(binding.datePickerCustom)
        customizeDatePicker(binding.datePickerCustom)

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

              /*  monthPicker.setOnValueChangedListener { numberPicker, i, i2 ->
                    monthPicker.displayedValues = getDisplayValues(1, 12, "월")
                    monthPicker.announceForAccessibility( "${yearPicker.value}년 ${monthPicker.value}월")
                }*/
            }

            yearPicker?.let {
                yearPicker.minValue = 1900
                yearPicker.maxValue = 2100
                yearPicker.displayedValues = getDisplayValues(1900, 2100, "년")
                yearPicker.value = 2024
            }

        } catch (e: Exception) {
            e.printStackTrace()
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