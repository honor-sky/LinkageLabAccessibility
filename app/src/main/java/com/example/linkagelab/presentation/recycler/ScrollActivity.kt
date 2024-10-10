package com.example.linkagelab.presentation.recycler

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityScrollBinding
import com.example.linkagelab.presentation.HorizontalListAdapter

class ScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollBinding

    val country = arrayListOf("캐나다", "독일", "호주", "프랑스", "한국", "일본", "중국", "콩고", "브라질", "이집트")
    val number = (1..10).map { it.toString() }.toMutableList()
    val music = arrayListOf("노래1","노래2","노래3","노래4","노래5","노래6","노래7","노래8","노래9","노래10")
    val linkageLabKrew = mutableListOf("올리","하퍼","미아","이든","칸","아마라","레스더","웨인","리암","웬디","조셉","카일라","맥스","폴라","델리",
        "로웰","마리","월드","엘리자","헤이즐","제임스","제니","카이","드웨이","민디","찰리","콘라드","히로","제이콥","제니퍼","카오","키미","라일라")

    lateinit var verticalAdapter : VerticalListAdapter
    lateinit var verticalCustomAdapter : VerticalListAdapter

    lateinit var horizontalNumberAdapter : HorizontalListAdapter
    lateinit var horizontalMusicAdapter : HorizontalListAdapter

    var currentMusicIdx = 0
    var displayedItemsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initListener()

    }

    fun initAdapter() {

        verticalAdapter = VerticalListAdapter()
        verticalCustomAdapter = VerticalListAdapter()
        horizontalNumberAdapter = HorizontalListAdapter()
        horizontalMusicAdapter  = HorizontalListAdapter()

        verticalAdapter.setData(country)
        horizontalNumberAdapter.setData(music)
        horizontalMusicAdapter.setData(music)
        // 더보기 목록 - 첫 아이템 추가
        val newItems = linkageLabKrew.drop(displayedItemsCount).take(5).toMutableList() // 다음 5개 아이템 가져오기
        verticalCustomAdapter.addItems(newItems)
        displayedItemsCount += newItems.size

        binding.verticalRecyclerView.adapter = verticalAdapter
        binding.verticalRecyclerViewCustom.adapter = verticalCustomAdapter
        binding.horizontalRecyclerViewBasic.adapter = horizontalNumberAdapter
        binding.horizontalRecyclerViewCustom.adapter = horizontalNumberAdapter
        binding.horizontalRecyclerViewCustom2.adapter = horizontalNumberAdapter
    }


    fun initListener() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.leftBtn.setOnClickListener {
            scrollRecyclerView(-1)
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }

        binding.rightBtn.setOnClickListener {
            scrollRecyclerView(1)
        }

        binding.moreBtn.setOnClickListener {
            loadMoreItems()
        }

        binding.verticalRecyclerViewCustom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 스크롤이 끝에 도달했을 때 "더보기" 버튼을 보여줍니다.
                if (!recyclerView.canScrollVertically(1)) {
                    binding.moreBtn.visibility = View.VISIBLE
                } else {
                    binding.moreBtn.visibility = View.GONE
                }
            }
        })

        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "다음 항목"
        ) { _, _ ->

            val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)

            // 접근성 이벤트로 음성 출력
            binding.horizontalRecyclerViewCustom2.contentDescription = "${item}, 총 ${horizontalMusicAdapter.itemCount}개의 항목 중 ${currentMusicIdx + 1}번째 항목"
            binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)


            if (currentMusicIdx < horizontalMusicAdapter.itemCount - 1) {
                binding.horizontalRecyclerViewCustom2.smoothScrollToPosition(currentMusicIdx)
                currentMusicIdx++


            } else {
                currentMusicIdx = horizontalMusicAdapter.itemCount - 1
                //binding.horizontalRecyclerViewCustom2.contentDescription = "마지막 항목 입니다."
                //binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            }
            true
        }

        // '이전 항목' 작업에서 smoothScrollToPosition을 호출하고 스크롤 완료 후에 음성 출력
        ViewCompat.addAccessibilityAction(
            binding.horizontalRecyclerViewCustom2Layout,
            "이전 항목"
        ) { _, _ ->

            val item = horizontalMusicAdapter.stringList?.get(currentMusicIdx)

            // 접근성 이벤트로 음성 출력
            binding.horizontalRecyclerViewCustom2.contentDescription = "${item}, 총 ${horizontalMusicAdapter.itemCount}개의 항목 중 ${currentMusicIdx + 1}번째 항목"
            binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            if (currentMusicIdx > 0) {
                // 이전 항목으로 스크롤
                binding.horizontalRecyclerViewCustom2.smoothScrollToPosition(currentMusicIdx)
                currentMusicIdx--

            } else {
                currentMusicIdx = 0
                //binding.horizontalRecyclerViewCustom2.contentDescription = "첫번째 항목 입니다."
                //binding.horizontalRecyclerViewCustom2.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            }
            true
        }
    }




    private fun scrollRecyclerView(direction: Int) {
        // 현재 스크롤 위치를 가져온다
        val layoutManager = binding.horizontalRecyclerViewCustom.layoutManager as LinearLayoutManager
        val currentPosition = layoutManager.findFirstVisibleItemPosition()

        // 이동할 위치 계산 (3개 항목 단위로 이동)
        var targetPosition = currentPosition + direction * 3

        // 스크롤 이동
        if (targetPosition in 0 until binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(targetPosition)

            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "${targetPosition}번째 항목으로 스크롤 이동"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

        } else if(targetPosition < 0) {

            targetPosition = 1
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(0)
            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "항목 리스트의 처음에 도달"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

        } else if(targetPosition > binding.horizontalRecyclerViewCustom.adapter!!.itemCount) {
            targetPosition = binding.horizontalRecyclerViewCustom.adapter!!.itemCount
            binding.horizontalRecyclerViewCustom.smoothScrollToPosition(binding.horizontalRecyclerViewCustom.adapter!!.itemCount)

            // 현재 어떤 컴포넌트가 가장 앞에 있는지 안내
            binding.horizontalRecyclerViewCustom.contentDescription = "항목 리스트의 마지막에 도달"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }


    }



    fun loadMoreItems() {
        val newItems = linkageLabKrew.drop(displayedItemsCount).take(5).toMutableList() // 다음 5개 아이템 가져오기
        verticalCustomAdapter.addItems(newItems) // 어댑터에 추가

        binding.verticalRecyclerViewCustom.post {

            val viewHolder =
                binding.verticalRecyclerViewCustom.findViewHolderForAdapterPosition(displayedItemsCount)
            if (viewHolder != null) {
                val itemView = viewHolder.itemView

                itemView.requestFocus()
                itemView.announceForAccessibility("아래로 항목 추가됨")
                itemView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            }

            displayedItemsCount += newItems.size
            binding.moreBtn.visibility = if (displayedItemsCount < linkageLabKrew.size - 5) View.VISIBLE else View.GONE // 더 이상 아이템이 없으면 버튼 숨기기

        }


    }
}