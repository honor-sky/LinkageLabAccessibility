package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivitySheetCustomBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*

class CustomSheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySheetCustomBinding
    private lateinit var persistenetBottomSheet : BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySheetCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        persistenetBottomSheet = from(binding.bottomSheetLayout)
        persistenetBottomSheet.state = STATE_HIDDEN


        initListener()
    }

    fun initListener() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.textSheetexpandBtnCustom.setOnClickListener {
            binding.bottomSheet1Custom.textBottomSheetLayoutRoot.visibility = View.VISIBLE
            persistenetBottomSheet.state = STATE_COLLAPSED
        }

        binding.textSheetShrinkBtnCustom.setOnClickListener {
            binding.bottomSheet1Custom.textBottomSheetLayoutRoot.visibility = View.GONE
            persistenetBottomSheet.state = STATE_HIDDEN
        }

        persistenetBottomSheet.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_COLLAPSED -> {
                        bottomSheet.contentDescription = "화면 하단에 가게정보 페이지가 3분의 1 열림"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES

                        binding.bottomSheet1Custom.handelBar.contentDescription = "가게정보 페이지, 전체 보기"
                        // 두손가락으로 아래에서 위 또는 아래로 스와이프해서 확장, 축소 할 수 있습니다.

                        binding.bottomSheet1Custom.handelBar.setOnClickListener {
                            persistenetBottomSheet.state = STATE_EXPANDED
                        }

                        // 핸들바로 초점 이동
                        binding.bottomSheet1Custom.handelBar.requestFocus()
                        binding.bottomSheet1Custom.handelBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                    }

                    STATE_EXPANDED -> {
                        bottomSheet.contentDescription = "가게정보 페이지가 완전히 확장됨"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        // 뒷 배경은 초점 가지 않도록
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS

                        binding.bottomSheet1Custom.handelBar.contentDescription = "가게정보 페이지, 요약 보기"
                        // 두손가락으로 아래에서 위 또는 아래로 스와이프해서 확장, 축소 할 수 있습니다.

                        binding.bottomSheet1Custom.handelBar.setOnClickListener {
                            persistenetBottomSheet.state = STATE_COLLAPSED
                        }

                    }

                    STATE_HIDDEN -> {
                        bottomSheet.contentDescription = "가게정보 페이지가 닫힘"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES

                    }

                /*    STATE_HALF_EXPANDED -> {
                        bottomSheet.contentDescription = "바텀시트가 절반 확장 되었습니다"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        bottomSheet.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                    }*/

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })

    }
}

