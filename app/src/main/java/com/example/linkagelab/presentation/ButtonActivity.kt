package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityButtonBinding

class ButtonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }


    fun initListener() {

        binding.extendedFab.setOnClickListener {
            when (binding.extendedFab.isExtended) {
                true -> binding.extendedFab.shrink()
                false -> binding.extendedFab.extend()
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}