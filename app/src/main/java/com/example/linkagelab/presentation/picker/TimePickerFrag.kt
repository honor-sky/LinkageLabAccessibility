package com.example.linkagelab.presentation.picker

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.fragment.app.Fragment
import com.example.linkagelab.databinding.FragmentTimePickerBinding


class TimePickerFrag : Fragment() {

    private var _binding: FragmentTimePickerBinding? = null
    private val binding get() = _binding!!

    private
    val VIRTUAL_VIEW_ID_INCREMENT: Int = 1
    private
    val VIRTUAL_VIEW_ID_INPUT: Int = 2
    private
    val VIRTUAL_VIEW_ID_DECREMENT: Int = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        customizeDatePicker(binding.timePickerCustom)
        setPickerChild(binding.timePickerCustom)
        initAccessibility()

/*
        val dynamicCustomNumberPicker = CustomTimePicker(requireContext())
        dynamicCustomNumberPicker.minValue = 0
        dynamicCustomNumberPicker.maxValue = 100

        // 레이아웃에 추가
        binding.container.addView(dynamicCustomNumberPicker)*/


        return root
    }


    fun initAccessibility() {

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
                ampmPicker.setOnValueChangedListener { numberPicker, i, i2 ->
                    val customMessage = "${if(ampmPicker.value == 0) "오전" else "오후"} ${hourPicker.value}시 ${binding.timePickerCustom.minute}분으로 설정"
                    ampmPicker.announceForAccessibility(customMessage)
                }
            }

            hourPicker?.let {
                hourPicker.minValue = 1
                hourPicker.maxValue = 12
                hourPicker.displayedValues = getDisplayValues(1, 12, "시")
                hourPicker.value = 10
                hourPicker.setOnValueChangedListener { numberPicker, i, i2 ->

                    val customMessage = "${if(ampmPicker.value == 0) "오전" else "오후"} ${hourPicker.value}시 ${binding.timePickerCustom.minute}분으로 설정"
                    hourPicker.announceForAccessibility(customMessage)
                }


            }

            minutePicker?.let {
                minutePicker.minValue = 1
                minutePicker.maxValue = 12
                minutePicker.displayedValues = getDisplayValues(1, 60, "분")
                minutePicker.value = 10
                minutePicker.setOnValueChangedListener { numberPicker, i, i2 ->
                    val customMessage = "${if(ampmPicker.value == 0) "오전" else "오후"} ${hourPicker.value}시 ${binding.timePickerCustom.minute}분으로 설정"
                    minutePicker.announceForAccessibility(customMessage)
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun setPickerChild(viewGroup : ViewGroup) {

        for (i in 0 until  viewGroup.getChildCount()) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                setPickerChild(child)
            } else {
                //Log.d("TimePicker","${child}")
                if (child is EditText) {
                    val child = child as EditText
                    child.isClickable = false
                    child.focusable = View.NOT_FOCUSABLE

                  /*  child.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
                        override fun performAccessibilityAction(
                            host: View,
                            action: Int,
                            args: Bundle?
                        ): Boolean {
                         *//*   Log.d("ampmPicker","$action")

                            try {
                                val ampmPickerId =
                                    Resources.getSystem().getIdentifier("amPm", "id", "android")
                                val ampmPicker = binding.timePickerCustom.findViewById<NumberPicker>(ampmPickerId)
                                ampmPicker.announceForAccessibility("선택됨")
                                ampmPicker.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }*//*

                            return super.performAccessibilityAction(host, action, args)
                        }
                    })*/

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



    /*  fun createAccessibilityNodeInfo(virtualViewId: Int): AccessibilityNodeInfo {
          when (virtualViewId) {
              View.NO_ID -> return createAccessibilityNodeInfoForNumberPicker(
                  mScrollX, mScrollY,
                  mScrollX + (mRight - mLeft), mScrollY + (mBottom - mTop)
              )

              VIRTUAL_VIEW_ID_DECREMENT -> return createAccessibilityNodeInfoForVirtualButton(
                  VIRTUAL_VIEW_ID_DECREMENT,
                  getVirtualDecrementButtonText(), mScrollX, mScrollY,
                  mScrollX + (mRight - mLeft),
                  mTopSelectionDividerTop + mSelectionDividerHeight
              )

              VIRTUAL_VIEW_ID_INPUT -> return createAccessibiltyNodeInfoForInputText(
                  mScrollX,
                  mTopSelectionDividerTop + mSelectionDividerHeight,
                  mScrollX + (mRight - mLeft),
                  mBottomSelectionDividerBottom - mSelectionDividerHeight
              )

              VIRTUAL_VIEW_ID_INCREMENT -> return createAccessibilityNodeInfoForVirtualButton(
                  VIRTUAL_VIEW_ID_INCREMENT,
                  getVirtualIncrementButtonText(), mScrollX,
                  mBottomSelectionDividerBottom - mSelectionDividerHeight,
                  mScrollX + (mRight - mLeft), mScrollY + (mBottom - mTop)
              )
          }
          return super.createAccessibilityNodeInfo(virtualViewId)
      }*/

    fun createAccessibilityNodeInfoForNumberPicker(left : Int, top : Int, right : Int, bottom : Int) {

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}