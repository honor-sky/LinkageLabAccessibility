package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.core.view.ViewCompat.performAccessibilityAction
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityScrollBinding

class ScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollBinding

    val country = arrayListOf("캐나다", "독일", "호주", "프랑스", "한국", "일본", "중국", "콩고", "브라질", "이집트")
    val number = (1..10).map { it.toString() }.toMutableList()
    val music = arrayListOf("Happy","내 이름 맑음","Supernova", "한 페이지가 될 수 있게","녹아내려요","소나기","클락션","고민중독")
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
        //verticalCustomAdapter.setData(linkageLabKrew)
        loadMoreItems()

        horizontalNumberAdapter.setData(music)
        horizontalMusicAdapter.setData(music)

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
            binding.horizontalRecyclerViewCustom.contentDescription = "스크롤이 왼쪽으로 이동했습니다"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }

        binding.rightBtn.setOnClickListener {
            scrollRecyclerView(1)
            binding.horizontalRecyclerViewCustom.contentDescription = "스크롤이 오른쪽으로 이동했습니다"
            binding.horizontalRecyclerViewCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
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

        // "더보기" 버튼 클릭 리스너
        binding.moreBtn.setOnClickListener {
            loadMoreItems()
        }

        binding.horizontalRecyclerViewCustom2.setAccessibilityDelegate( object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                // 사용자 정의 작업 추가
                val customActionId = View.generateViewId()
                val customAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                    customActionId, "작업 실행" // TalkBack 메뉴에서 보여질 이름
                )

                // 작업을 추가하고 사용자 정의 기능 연결
                AccessibilityNodeInfoCompat.wrap(info).addAction(customAction)
            }
        })

       /* binding.horizontalRecyclerViewCustom2.setOnClickListener {
            performAccessibilityAction(customActionId, null)
        }*/


        binding.horizontalRecyclerViewCustom2.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // 기본 접근성 행동을 유지
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD)
            }

            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {
                    AccessibilityNodeInfo.ACTION_SCROLL_FORWARD -> {
                        // 다음 항목
                        if (currentMusicIdx < horizontalMusicAdapter.itemCount - 1) {
                            currentMusicIdx++
                            scrollToPosition(currentMusicIdx)
                        }

                        true
                    }

                    AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD -> {
                        // 이전 항목
                        if (currentMusicIdx > 0) {
                            currentMusicIdx--
                            scrollToPosition(currentMusicIdx)
                        }

                        true
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })

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

     fun scrollToPosition(position : Int) {
         binding.horizontalRecyclerViewCustom2.smoothScrollToPosition(position);
         val item  = binding.horizontalRecyclerViewCustom2.getLayoutManager()?.findViewByPosition(position)
        if (item != null) {
            item.requestFocus()
        }
    }


    fun loadMoreItems() {
        val newItems = linkageLabKrew.drop(displayedItemsCount).take(5).toMutableList() // 다음 5개 아이템 가져오기
        verticalCustomAdapter.addItems(newItems) // 어댑터에 추가

        // 새로 생긴 아이템의 가장 첫 항목으로 포커스
        binding.verticalRecyclerViewCustom.findViewHolderForAdapterPosition(displayedItemsCount)?.itemView?.requestFocus()

        displayedItemsCount += newItems.size // 표시된 아이템 수 증가
        binding.moreBtn.visibility = if (displayedItemsCount < linkageLabKrew.size) View.VISIBLE else View.GONE // 더 이상 아이템이 없으면 버튼 숨기기
    }
}