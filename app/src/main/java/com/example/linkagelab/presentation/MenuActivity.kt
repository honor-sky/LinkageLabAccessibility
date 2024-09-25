package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // 뒤로가기 처리
        }

        registerForContextMenu(binding.contextMenuBasic)

        setAccessibility()
        initListener()

    }

    fun setAccessibility() {
        binding.topAppBar.setNavigationContentDescription("뒤로가기")
        binding.topAppBar.isAccessibilityHeading = true
    }

    fun initListener() {

        binding.popupMenu.setOnClickListener { it ->
            val popupMenu = PopupMenu(this, it)
            popupMenu.inflate(R.menu.nav_menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.home_fragment -> Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show()
                    R.id.register_fragment -> Toast.makeText(this, "등록하기 메뉴 클릭", Toast.LENGTH_SHORT).show()
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
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home_fragment -> Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show()
            R.id.register_fragment -> Toast.makeText(this, "등록하기 메뉴 클릭", Toast.LENGTH_SHORT).show()
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
            R.id.register_fragment -> {
                Toast.makeText(this, "등록하기 메뉴 클릭", Toast.LENGTH_SHORT).show()
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