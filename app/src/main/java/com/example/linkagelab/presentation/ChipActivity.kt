package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityChipBinding
import com.example.linkagelab.databinding.ActivityTextBinding

class ChipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChipBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}