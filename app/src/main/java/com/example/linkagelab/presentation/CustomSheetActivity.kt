package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
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

        // 커스텀
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
                        bottomSheet.contentDescription = "바텀시트가 3분의 1 확장 되었습니다"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                    }

                    STATE_EXPANDED -> {
                        bottomSheet.contentDescription = "바텀시트가 완전히 확장 되었습니다"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        // 뒷 배경은 초점 가지 않도록
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
                    }

                    STATE_HALF_EXPANDED -> {
                        bottomSheet.contentDescription = "바텀시트가 절반 확장 되었습니다"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        bottomSheet.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                    }

                    STATE_HIDDEN -> {
                        bottomSheet.contentDescription = "바텀시트가 숨김 처리 되었습니다"
                        bottomSheet.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
                        binding.mainParentContent.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })

    }
}

