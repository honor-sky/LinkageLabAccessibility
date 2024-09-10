package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityAppbarBinding
import com.example.linkagelab.databinding.ActivityChipBinding

class AppBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}