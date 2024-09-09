package com.example.linkagelab.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityNavigationBinding
import com.example.linkagelab.databinding.ActivitySheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
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


        binding.expandBtn.setOnClickListener {
            persistenetBottomSheet.state = STATE_HALF_EXPANDED
        }

        binding.shrinkBtn.setOnClickListener {
            persistenetBottomSheet.state = STATE_HIDDEN
        }

    }
}