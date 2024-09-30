package com.example.linkagelab.presentation

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.linkagelab.databinding.FragmentTimePickerBinding
import java.lang.reflect.Field

class TimePickerFrag : Fragment() {

    private var _binding: FragmentTimePickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        customizeDatePicker(binding.timePickerCustom)

        setPickerChild(binding.timePickerCustom)


        return root
    }

    fun customizeDatePicker(timePicker: TimePicker) {
        try {
            val ampmPickerId = Resources.getSystem().getIdentifier("amPm", "id", "android")
            val hourPickerId = Resources.getSystem().getIdentifier("hour", "id", "android")
            val minutePickerId = Resources.getSystem().getIdentifier("minute", "id", "android")

            val ampmPicker = timePicker.findViewById<NumberPicker>(ampmPickerId)
            val hourPicker = timePicker.findViewById<NumberPicker>(hourPickerId)
            val minutePicker = timePicker.findViewById<NumberPicker>(minutePickerId)

            ampmPicker?.let {
                //setAmPMChild(ampmPicker)
            }

            hourPicker?.let {
                hourPicker.minValue = 1
                hourPicker.maxValue = 12
                hourPicker.displayedValues = getDisplayValues(1, 12, "시")
                hourPicker.value = 10
                //setPickerChild(hourPicker)

            }

            minutePicker?.let {
                minutePicker.minValue = 1
                minutePicker.maxValue = 12
                minutePicker.displayedValues = getDisplayValues(1, 60, "분")
                minutePicker.value = 10
                //setPickerChild(minutePicker)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun setAmPMChild(viewGroup : ViewGroup) {
        for (i in 0 until  viewGroup.getChildCount()) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                setPickerChild(child)
            } else {
                if(child is EditText) {
                    child.isClickable = false
                    child.focusable = View.NOT_FOCUSABLE
                }
            }
        }
    }

    fun setPickerChild(viewGroup : ViewGroup) {

        for (i in 0 until  viewGroup.getChildCount()) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                setPickerChild(child)
            } else {
                Log.d("TimePicker","${child}")
                if(child is EditText) {
                    child.isClickable = false
                    child.focusable = View.NOT_FOCUSABLE
                    //child.inputType = InputType.TYPE_CLASS_NUMBER

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



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}