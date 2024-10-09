package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        initListener()

        // 맵화면에 바텀시트 동작 달음
        //binding.mainContent.setBottomSheetBehavior(persistenetBottomSheet)



    }


    fun initListener() {

        binding.backBtn.setOnClickListener {
            finish()
        }

        // 텍스트 시트
        binding.textSheetexpandBtn.setOnClickListener {
            //binding.bottomSheet2.buttonBottomSheetLayoutRoot.visibility = View.GONE
            binding.bottomSheet1.textBottomSheetLayoutRoot.visibility = View.VISIBLE

            persistenetBottomSheet.state = STATE_COLLAPSED
        }

        binding.textSheetShrinkBtn.setOnClickListener {
            binding.bottomSheet1.textBottomSheetLayoutRoot.visibility = View.GONE
            persistenetBottomSheet.state = STATE_HIDDEN
        }



        // 버튼 시트
        /*
        binding.buttonSheetexpandBtn.setOnClickListener {
            binding.bottomSheet1.textBottomSheetLayoutRoot.visibility = View.GONE
            binding.bottomSheet2.buttonBottomSheetLayoutRoot.visibility = View.VISIBLE
            persistenetBottomSheet.state = STATE_COLLAPSED
        }

        binding.buttonSheetShrinkBtn.setOnClickListener {
            binding.bottomSheet2.buttonBottomSheetLayoutRoot.visibility = View.GONE
            persistenetBottomSheet.state = STATE_HIDDEN
        }

       binding.bottomSheet2.shareBtn.setOnClickListener {
            Toast.makeText(this, "공유하기 버튼 클릭", Toast.LENGTH_LONG).show()

        }

        binding.bottomSheet2.copyLinkBtn.setOnClickListener {
            Toast.makeText(this, "링크 복사 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        binding.bottomSheet2.bookmarkBtn.setOnClickListener {
            Toast.makeText(this, "북마크 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        binding.bottomSheet2.callBtn.setOnClickListener {
            Toast.makeText(this, "전화걸기 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        binding.bottomSheet2.mailBtn.setOnClickListener {
            Toast.makeText(this, "메일 보내기 버튼 클릭", Toast.LENGTH_LONG).show()
        }

        binding.bottomSheet2.deleteBtn.setOnClickListener {
            Toast.makeText(this, "삭제하기 버튼 클릭", Toast.LENGTH_LONG).show()
        }*/

    }


}