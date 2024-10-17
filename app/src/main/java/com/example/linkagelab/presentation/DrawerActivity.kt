package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityDrawerBinding


class DrawerActivity : AppCompatActivity() {


    private lateinit var binding:ActivityDrawerBinding


    companion object {
        private const val TAG_HOME = "home_fragment"
        private const val TAG_BOOKMARK = "bookmark_fragment"
        private const val TAG_MYPAGE = "mypage_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // appBar
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        binding.appBarMain.drawerBrn.contentDescription = "탐색창, 열기, 버튼"

        // 초기 화면
        if (savedInstanceState == null) {
            setFragment(TAG_HOME, HomeFragment())
        }

        initListener()
    }

    fun initListener() {
        binding.appBarMain.backBtn.setOnClickListener {
            finish()
        }

        binding.appBarMain.drawerBrn.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_btn -> setFragment(TAG_HOME, HomeFragment())
                R.id.bookmark_btn -> setFragment(TAG_BOOKMARK, BookmarkFragment())
                R.id.mypage_btn -> setFragment(TAG_MYPAGE, MypageFragment())
            }
            menuItem.isChecked = true

            binding.drawerLayout.closeDrawer(GravityCompat.START) // 아이템 선택 후 Drawer 닫기
            true
        }

    }

    fun setFragment(tag : String, fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commitAllowingStateLoss()
    }


}