package com.example.linkagelab.presentation

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.accessibility.MenuAccessibilityService
import com.example.linkagelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAccessibility()
        initListener()



    }

    fun setAccessibility() {

    }


    fun initListener() {
        binding.editTextTestBtn.setOnClickListener {
            startActivity(Intent(this, EditTextActivity::class.java))
        }

        binding.buttonTextBtn.setOnClickListener {
            startActivity(Intent(this, ButtonActivity::class.java))
        }

        binding.pickerTestBtn.setOnClickListener {
            startActivity(Intent(this, PickerActivity::class.java))
        }

        binding.ToastTestBtn.setOnClickListener {
            startActivity(Intent(this, ProgressBarActivity::class.java))
        }

        binding.bottomSheetTestBtn.setOnClickListener {
            startActivity(Intent(this, SheetActivity::class.java))
        }

        binding.customBottomSheetTestBtn.setOnClickListener {
            startActivity(Intent(this, CustomSheetActivity::class.java))
        }

        binding.sideSheetTestBtn.setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))

        }

        binding.scrollTestBtn.setOnClickListener {
            startActivity(Intent(this, ScrollActivity::class.java))
        }

        binding.MenuTestBtn.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        binding.viewPagerTestBtn.setOnClickListener {
            startActivity(Intent(this, ViewpagerActivity::class.java))
        }


    }

    private fun isAccessibilityServiceEnabled(context: Context, service: Class<out AccessibilityService>): Boolean {
        val expectedComponentName = ComponentName(context, service)

        val enabledServicesSetting = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        val colonSplitter = TextUtils.SimpleStringSplitter(':')
        colonSplitter.setString(enabledServicesSetting)

        while (colonSplitter.hasNext()) {
            val componentNameString = colonSplitter.next()
            val enabledComponentName = ComponentName.unflattenFromString(componentNameString)
            if (enabledComponentName != null && enabledComponentName == expectedComponentName)
                return true
        }

        return false
    }

}