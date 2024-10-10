package com.example.linkagelab.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityDrawerCustomBinding

class CustomDrawerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDrawerCustomBinding


    companion object {
        private const val TAG_HOME = "home_fragment"
        private const val TAG_BOOKMARK = "bookmark_fragment"
        private const val TAG_MYPAGE = "mypage_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        // HomeButton을 노출 시킴
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)


        if (savedInstanceState == null) {
            setFragment(TAG_HOME, HomeFragment())
        }

        binding.appBarMain.drawerBrn.contentDescription = "탐색창, 열기, 버튼"

        initListener()


    }

    fun initListener() {
        binding.appBarMain.backBtn.setOnClickListener {
            finish()
        }

        binding.appBarMain.drawerBrn.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

         binding.navView.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
             override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo) {
                 super.onInitializeAccessibilityNodeInfo(host, info)
                 info?.text = "탐색창이 열림, 두손가락으로 오른쪽에서 왼쪽으로 밀어서 닫을 수 있습니다."
                 info.addAction(AccessibilityEvent.TYPE_VIEW_FOCUSED)

             }
         })


        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
               override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
               }

               override fun onDrawerOpened(drawerView: View) {
                   binding.drawerLayout.announceForAccessibility("두손가락으로 오른쪽에서 왼쪽으로 밀어서 닫을 수 있습니다.\n닫기 작업 사용 가능, 세손가락으로 터치하여 봅니다.")
               }
               override fun onDrawerClosed(drawerView: View) {
                   binding.drawerLayout.announceForAccessibility("탐색창 닫힘")
               }

               override fun onDrawerStateChanged(newState: Int) {
               }
           });

        binding.menuHome.setOnClickListener {
            setFragment(TAG_HOME, HomeFragment())
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.menuBookmark.setOnClickListener {
            setFragment(TAG_BOOKMARK, BookmarkFragment())
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.menuMypage.setOnClickListener {
            setFragment(TAG_MYPAGE, MypageFragment())
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        /*
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> setFragment(TAG_HOME, HomeFragment())
                R.id.menu_bookmark -> setFragment(TAG_BOOKMARK, BookmarkFragment())
                R.id.menu_mypage -> setFragment(TAG_MYPAGE, MypageFragment())
            }
            menuItem.isChecked = true

            binding.drawerLayout.closeDrawer(GravityCompat.START) // 아이템 선택 후 Drawer 닫기
            true
        }

         */


    }

    fun setFragment(tag : String, fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commitAllowingStateLoss()
    }


}