package com.example.linkagelab.presentation

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityService.GESTURE_SWIPE_DOWN
import android.accessibilityservice.AccessibilityService.GESTURE_SWIPE_UP
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setAccessibilityDelegate
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.contextMenuBasic)
        initListener()

    }

    fun initListener() {

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        binding.popupMenu.setOnClickListener { it ->
            val popupMenu = PopupMenu(this, it)
            popupMenu.inflate(R.menu.nav_menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.first_item -> Toast.makeText(this, "첫번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                    R.id.second_item -> Toast.makeText(this, "두번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                    R.id.third_item -> Toast.makeText(this, "세번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                }
                true
            }

            popupMenu.show()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.first_item -> Toast.makeText(this, "첫번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
            R.id.second_item -> Toast.makeText(this, "두번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
            R.id.third_item -> Toast.makeText(this, "세번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.first_item -> {
                Toast.makeText(this, "첫번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.second_item -> {
                Toast.makeText(this, "두번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.third_item -> {
                Toast.makeText(this, "세번째 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }







}