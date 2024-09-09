package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityChipBinding
import com.example.linkagelab.databinding.ActivityNavigationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class PickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChipBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun datePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()
    }

    fun timePicker() {
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .build()
    }
}