package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityNavigationBinding
import com.example.linkagelab.databinding.ActivitySheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN

class SheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySheetBinding
    private lateinit var persistenetBottomSheet : BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        persistenetBottomSheet = BottomSheetBehavior.from(binding.bottomSheetLayout)
        persistenetBottomSheet.state = STATE_HIDDEN

        // 맵화면에 바텀시트 동작 달음
        //binding.mainContent.setBottomSheetBehavior(persistenetBottomSheet)

        binding.bottomSheetLayout.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                when(persistenetBottomSheet.state) {
                    STATE_COLLAPSED ->  info?.text = "바텀시트가 열렸습니다. 손가락으로 위아래로 스와이프해 조절하세요."
                    STATE_HALF_EXPANDED -> info?.text = "바텀시트가 절반 확장되었습니다. 두 손가락으로 위아래로 스와이프해 조절하세요."
                    STATE_EXPANDED -> info?.text = "바텀시트가 완전히 확장되었습니다. 두 손가락으로 아래로 스와이프하면 닫힙니다. "
                }

            }
        })


        binding.expandBtn.setOnClickListener {
            persistenetBottomSheet.state = STATE_COLLAPSED
        }

        binding.shrinkBtn.setOnClickListener {
            persistenetBottomSheet.state = STATE_HIDDEN
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}