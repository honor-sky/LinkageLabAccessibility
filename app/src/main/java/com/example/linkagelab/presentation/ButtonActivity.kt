package com.example.linkagelab.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.databinding.ActivityButtonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.RangeSlider


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

    @SuppressLint("NewApi")
    fun setAccessibility() {

        binding.checkBox1Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {

            @RequiresApi(Build.VERSION_CODES.R)
            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {

                    AccessibilityNodeInfo.ACTION_CLICK -> {
                        binding.checkBox1Custom.toggle()

                        val message = if (binding.checkBox1Custom.isChecked) {
                            "선택함"
                        } else {
                            "선택안함"
                        }
                        binding.checkBox1Custom.stateDescription = message

                        return true
                    }

                    AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {
                        val message = if (binding.checkBox1Custom.isChecked) {
                            "선택됨"
                        } else {
                            "선택안됨"
                        }
                        binding.checkBox1Custom.stateDescription = message
                        super.performAccessibilityAction(host, action, args)
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })

        binding.checkBox2Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {

                    AccessibilityNodeInfo.ACTION_CLICK -> {
                        binding.checkBox2Custom.toggle()

                        val message = if (binding.checkBox2Custom.isChecked) {
                            "선택함"
                        } else {
                            "선택안함"
                        }
                        binding.checkBox2Custom.stateDescription = message

                        return true
                    }

                    AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {
                        val message = if (binding.checkBox2Custom.isChecked) {
                            "선택됨"
                        } else {
                            "선택안됨"
                        }
                        binding.checkBox2Custom.stateDescription = message
                        super.performAccessibilityAction(host, action, args)
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })

        binding.checkBox3Custom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {

                    AccessibilityNodeInfo.ACTION_CLICK -> {
                        binding.checkBox3Custom.toggle()

                        val message = if (binding.checkBox3Custom.isChecked) {
                            "선택함"
                        } else {
                            "선택안함"
                        }
                        binding.checkBox3Custom.stateDescription = message

                        return true
                    }

                    AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS -> {

                        val message = if (binding.checkBox3Custom.isChecked) {
                            "선택됨"
                        } else {
                            "선택안됨"
                        }
                        binding.checkBox3Custom.stateDescription = message
                        super.performAccessibilityAction(host, action, args)
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })


       /*
         binding.seekBarCustom.addOnChangeListener { slider, value, fromUser ->
            if (fromUser) {
                // thumb이 사용자의 조작으로 변경된 경우
                binding.seekBarCustom.contentDescription = "현재 값은 $value 입니다."
                //binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            }
         }
        */

        binding.seekBarCustom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // 기본 접근성 행동을 유지
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD)
            }

            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {
                    AccessibilityNodeInfo.ACTION_SCROLL_FORWARD -> {
                        // 슬라이더 값 증가
                        if(binding.seekBarCustom.value +  (binding.seekBarCustom.stepSize * 5) > binding.seekBarCustom.valueTo) {
                            binding.seekBarCustom.value = binding.seekBarCustom.valueTo
                        } else {
                            binding.seekBarCustom.value = binding.seekBarCustom.value +  (binding.seekBarCustom.stepSize * 5)
                        }
                        binding.seekBarCustom.contentDescription =  "값, ${binding.seekBarCustom.value}"
                        binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

                        true
                    }
                    AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD -> {
                        // 슬라이더 값 감소
                        if(binding.seekBarCustom.value - (binding.seekBarCustom.stepSize * 5) > binding.seekBarCustom.valueFrom) {
                            binding.seekBarCustom.value = binding.seekBarCustom.valueFrom
                        } else {
                            binding.seekBarCustom.value = binding.seekBarCustom.value - (binding.seekBarCustom.stepSize * 5)
                        }
                        binding.seekBarCustom.contentDescription =  "값, ${binding.seekBarCustom.value}"
                        binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

                        true
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })



    }



    fun initListener() {


        binding.plusBtn.setOnClickListener {
            if( binding.seekBarCustom.value == binding.seekBarCustom.valueTo) {
                binding.seekBarCustom.contentDescription = "현재 최댓값입니다, 값, ${binding.seekBarCustom.value}"
                binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            } else {
                binding.seekBarCustom.value += 1

                binding.seekBarCustom.contentDescription = "값, ${binding.seekBarCustom.value}"
                binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            }

        }

        binding.minusBtn.setOnClickListener {
            if( binding.seekBarCustom.value == binding.seekBarCustom.valueFrom) {
                binding.seekBarCustom.contentDescription = "현재 최솟값입니다, 값, ${binding.seekBarCustom.value}"
                binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            } else {
                binding.seekBarCustom.value -= 1

                binding.seekBarCustom.contentDescription = "값, ${binding.seekBarCustom.value}"
                binding.seekBarCustom.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
            }

        }


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



    }

    private fun speakValue(message: String) {
        // AccessibilityManager를 통해 TalkBack 음성 피드백
        val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        if (accessibilityManager.isEnabled) {
            val speech = android.speech.tts.TextToSpeech(this, null).apply {
                // TTS를 통해 음성을 출력
                speak(message, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }
}