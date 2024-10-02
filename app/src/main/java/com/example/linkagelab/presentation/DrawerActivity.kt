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

        supportActionBar?.setHomeButtonEnabled(true)
        // HomeButton을 노출 시킴
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)


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
                R.id.nav_home -> setFragment(TAG_HOME, HomeFragment())
                R.id.nav_bookmark -> setFragment(TAG_BOOKMARK, RegisterFragment())
                R.id.nav_mypage -> setFragment(TAG_MYPAGE, MypageFragment())
            }
            menuItem.isChecked = true
            // 아이템 선택 후 Drawer 닫기
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun setFragment(tag : String, fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commitAllowingStateLoss()
    }




/*    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }*/
}