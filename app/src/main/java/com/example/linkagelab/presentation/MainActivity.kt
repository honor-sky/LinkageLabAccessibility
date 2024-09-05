package com.example.linkagelab.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시스템바 설정
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
           val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
           v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
           insets
       }*/


        binding.testBtn.setOnClickListener {
            sendAccessibilityEvent("테스트 입니다!!")
        }

        // contentDescription 커스터마이징
        binding.testBtn2.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                // 접근성 노드 정보 커스터마이즈
                //info.contentDescription = "커스터마이즈된 설명"
            }

            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                // 접근성 액션 커스터마이즈
                return when (action) {
                    AccessibilityNodeInfo.ACTION_CLICK -> {
                        // 버튼 클릭 시의 커스터마이즈된 행동 정의
                        true
                    }
                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        }

    }




    fun sendAccessibilityEvent(message: String) {
        val event = AccessibilityEvent.obtain()
        event.eventType = AccessibilityEvent.TYPE_ANNOUNCEMENT
        event.className = this.javaClass.name
        event.packageName = this.packageName
        event.text.add(message)

        try {
            val manager: AccessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            manager.sendAccessibilityEvent(event)
        } catch (e: Exception) {

        }
    }
}