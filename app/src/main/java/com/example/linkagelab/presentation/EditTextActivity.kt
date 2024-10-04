package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.linkagelab.databinding.ActivityEdittextBinding

class EditTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEdittextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEdittextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

       /* binding.ellipEditText.doAfterTextChanged { it ->
            binding.ellipText.text = binding.ellipEditText.text.toString()
        }*/

    }
}