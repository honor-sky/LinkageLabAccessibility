package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityNavigationBinding
import com.example.linkagelab.databinding.ActivitySheetBinding

class SheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySheetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}