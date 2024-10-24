package com.example.linkagelab.presentation.picker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.NumberPicker
import java.lang.reflect.Field

@SuppressLint("SoonBlockedPrivateApi")
class CustomTimePicker(context: Context) : NumberPicker(context){

    init {
        try {
            // 리플렉션을 사용하여 NumberPicker의 private 필드 'mValue'에 접근
            val field: Field = NumberPicker::class.java.getDeclaredField("mInputText")
            field.isAccessible = true // 접근 가능하게 설정
            val value = field

            //Log.d("CustomTimePicker","${field.}")
            //Log.d("CustomTimePicker", "Private mValue: $value")
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}