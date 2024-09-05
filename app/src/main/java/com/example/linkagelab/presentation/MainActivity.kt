package com.example.linkagelab.presentation

import android.content.Context
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.testBtn.setOnClickListener {
            sendAccessibilityEvent("테스트 입니다!!")
        }



        // 시스템바 설정
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
           val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
           v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
           insets
       }*/


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