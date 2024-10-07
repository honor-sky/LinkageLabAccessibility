package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        binding.horizontalRecyclerViewBasic.adapter = horizontalNumberAdapter
        binding.horizontalRecyclerViewCustom.adapter = horizontalNumberAdapter

        initListener()

    }


    fun initListener() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.leftBtn.setOnClickListener {
            scrollRecyclerView(-1)
            binding.horizontalRecyclerViewCustom.contentDescription = "스크롤이 왼쪽으로 이동했습니다"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }

        binding.rightBtn.setOnClickListener {
            scrollRecyclerView(1)
            binding.horizontalRecyclerViewCustom.contentDescription = "스크롤이 오른쪽으로 이동했습니다"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }


      /*  binding.verticalRecyclerViewCustom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 다음페이지 존재 여부 확인
                if(viewmodel.isEnd == true) return

                // 아직 로딩중인지 확인
                if (viewmodel._isProgress.value == false) { // 아직 로딩중이면 호출 x
                    // 스크롤이 끝에 도달했는지 확인
                    if (!binding.verticalRecyclerViewCustom.canScrollVertically(1)) { // 더이상 하단으로 내려갈 수 없음
                        // 값 더 추가 (10개씩)

                    }
                }

            }
        })*/
    }

    private fun scrollRecyclerView(direction: Int) {
        // 현재 스크롤 위치를 가져온다
        val layoutManager = binding.horizontalRecyclerViewCustom.layoutManager as LinearLayoutManager
        val currentPosition = layoutManager.findFirstVisibleItemPosition()

        // 이동할 위치 계산 (5개 항목 단위로 이동)
        val targetPosition = currentPosition + direction * 5

        // 스크롤 이동
        if (targetPosition in 0 until binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(targetPosition)
        } else if(targetPosition < 0) {
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(0)
        } else if(targetPosition > binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(binding.horizontalRecyclerViewCustom.adapter!!.itemCount)
        }
    }


    fun addItem() {

    }

}