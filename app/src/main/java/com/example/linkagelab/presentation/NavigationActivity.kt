package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityChipBinding
import com.example.linkagelab.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}