package com.example.linkagelab.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityButtonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ButtonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.extendedMainFab.shrink()


        setAccessibility()
        initListener()

    }

    fun setAccessibility() {

        binding.checkBox1Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                info.isCheckable = false
                info.isChecked = false

             /*   if (binding.checkBox1Custom.isChecked) {
                    info.contentDescription = "선택됨, 1번, 전환하려면 두 번 탭하세요"
                } else {
                    info.contentDescription = "선택안됨, 1번, 전환하려면 두 번 탭하세요"
                }*/

            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                // 접근성 액션 커스터마이즈
                return when (action) {
                    AccessibilityNodeInfo.ACTION_CLICK -> {

                        // 체크박스 상태 변경
                        binding.checkBox1Custom.toggle()

                        // 커스텀 TalkBack 메시지 출력 (한국어로 상태 출력)
                        val message = if (binding.checkBox1Custom.isChecked) {
                            "선택됨"
                        } else {
                            "선택 안됨"
                        }

                        // TalkBack으로 커스텀 메시지 출력
                        binding.checkBox1Custom.stateDescription = message

                        return true // 기본 액션 처리를 막음
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })

        binding.checkBox2Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                if (binding.checkBox2Custom.isChecked) {
                    info.contentDescription = "선택됨, 2번, 전환하려면 두 번 탭하세요"
                } else {
                    info.contentDescription = "선택안됨, 2번, 전환하려면 두 번 탭하세요"
                }

            }
        })

        binding.checkBox3Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                if (binding.checkBox3Custom.isChecked) {
                    info.contentDescription = "선택됨, 3번, 전환하려면 두 번 탭하세요"
                } else {
                    info.contentDescription = "선택안됨, 3번, 전환하려면 두 번 탭하세요"
                }

            }
        })

    }


    fun initListener() {

        binding.extendedMainFab.setOnClickListener {
            when (binding.extendedMainFab.isExtended) {
                true -> {

                    binding.extendedMainFab.shrink()

                    binding.extendedFab1.visibility = View.GONE
                    binding.extendedFab2.visibility = View.GONE
                    binding.extendedFab3.visibility = View.GONE
                }
                false -> {

                    binding.extendedMainFab.extend()

                    binding.extendedFab1.visibility = View.VISIBLE
                    binding.extendedFab2.visibility = View.VISIBLE
                    binding.extendedFab3.visibility = View.VISIBLE
                }
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.floatingActionButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("FAB 클릭")
                .setMessage("FAB가 클릭되어 활성화 된 알림창 입니다.")
                .setPositiveButton("확인") {  dialog, which ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.callBtn.setOnClickListener {

        }

        binding.mailBtn.setOnClickListener {

        }

        binding.bookmarkBtn.setOnClickListener {

        }
    }
}