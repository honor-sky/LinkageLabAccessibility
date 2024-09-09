package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityButtonBinding
import com.example.linkagelab.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}