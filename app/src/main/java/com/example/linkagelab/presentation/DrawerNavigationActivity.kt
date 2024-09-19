package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityNavigationBinding

class DrawerNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding


    companion object {
        private const val TAG_HOME = "home_fragment"
        private const val TAG_REGISTER = "register_fragment"
        private const val TAG_MYPAGE = "mypage_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        setFragment(Companion.TAG_HOME, HomeFragment())

    }

    fun initListener() {
        binding.backBtn.setOnClickListener {
            finish()
        }

      /*  binding.navView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_fragment -> setFragment(Companion.TAG_HOME, HomeFragment())
                R.id.register_fragment -> setFragment(TAG_REGISTER, RegisterFragment())
                R.id.mypage_fragment -> setFragment(TAG_MYPAGE, MypageFragment())
            }
            true
        }*/
    }

    fun setFragment(tag : String, fragment : Fragment) {
        val manager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.main_content, fragment, tag)
        }

        val home = manager.findFragmentByTag(Companion.TAG_HOME)
        val register = manager.findFragmentByTag(TAG_REGISTER)
        val myPage = manager.findFragmentByTag(TAG_MYPAGE)


        if (home != null){
            fragTransaction.hide(home)
        }

        if (register != null){
            fragTransaction.hide(register)
        }

        if (myPage != null) {
            fragTransaction.hide(myPage)
        }

        if (tag == TAG_HOME) {
            if (home!=null){
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_REGISTER) {
            if (register != null) {
                fragTransaction.show(register)
            }
        }

        else if (tag == TAG_MYPAGE){
            if (myPage != null){
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()





    }

}