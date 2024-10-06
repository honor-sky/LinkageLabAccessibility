package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivitySearchviewBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    fun initListener() {
        binding.searchBar.setOnSearchClickListener {
            // 검색 결과에 맞는 결과만 반환

        }
    }
}