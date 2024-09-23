package com.example.linkagelab.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityScrollBinding

class ScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollBinding

    val country = arrayListOf("캐나다", "독일", "호주", "프랑스", "한국", "일본", "중국", "콩고", "브라질", "이집트")
    val number = (1..10).map { it.toString() }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val verticalAdapter = VerticalListAdapter()
        val horizontalNumberAdapter = HorizontalListAdapter()

        verticalAdapter.setData(country)
        horizontalNumberAdapter.setData(number)

        binding.verticalRecyclerView.adapter = verticalAdapter
        binding.horizontalRecyclerView.adapter = horizontalNumberAdapter

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}