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
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)



        registerForContextMenu(binding.contextMenuBasic)

        //setAccessibility()
        initListener()

    }


/*    fun setAccessibility() {
        binding.topAppBar.navigationContentDescription = "뒤로가기"
        binding.contextMenuCustom.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
                return when (action) {

                    AccessibilityNodeInfo.ACTION_LONG_CLICK -> {
                       // context menu 열기
                        Toast.makeText(this@MenuActivity, "두 손가락으로 두 번 탭 감지됨", Toast.LENGTH_SHORT).show()

                        return false
                    }

                    else -> super.performAccessibilityAction(host, action, args)
                }
            }
        })
    }*/

    fun initListener() {

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        binding.popupMenu.setOnClickListener { it ->
            val popupMenu = PopupMenu(this, it)
            popupMenu.inflate(R.menu.nav_menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.home_fragment -> Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show()
                    R.id.bookmark_fragment -> Toast.makeText(this, "북마크 메뉴 클릭", Toast.LENGTH_SHORT).show()
                    R.id.mypage_fragment -> Toast.makeText(this, "마이페이지 메뉴 클릭", Toast.LENGTH_SHORT).show()
                }
                true
            }

            popupMenu.show()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)

        val homeMenuItem = menu?.findItem(R.id.home_fragment)
        homeMenuItem!!.actionView?.contentDescription = "홈, 버튼"

        val bookmarkMenuItem = menu?.findItem(R.id.bookmark_fragment)
        bookmarkMenuItem!!.actionView?.contentDescription = "북마크, 버튼"

        val mypageMenuItem = menu?.findItem(R.id.mypage_fragment)
        mypageMenuItem!!.actionView?.contentDescription = "마이페이지, 버튼"

        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home_fragment -> Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show()
            R.id.bookmark_fragment -> Toast.makeText(this, "북마크 메뉴 클릭", Toast.LENGTH_SHORT).show()
            R.id.mypage_fragment -> Toast.makeText(this, "마이페이지 메뉴 클릭", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_fragment -> {
                Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.bookmark_fragment -> {
                Toast.makeText(this, "북마크 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mypage_fragment -> {
                Toast.makeText(this, "마이페이지 메뉴 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }





}