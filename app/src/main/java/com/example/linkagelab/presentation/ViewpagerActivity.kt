package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.linkagelab.databinding.ActivityViewpagerBinding
import com.google.android.material.tabs.TabLayout

class ViewpagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewpagerBinding

    val frag1 = HomeFragment()
    val frag2 = RegisterFragment()
    val frag3 = MypageFragment()
    val fragList = arrayOf(frag1, frag2, frag3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewpagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager(binding.viewpager)

        initListener()
    }

    fun initListener() {

        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.leftBtn.setOnClickListener {
            binding.viewpager.currentItem = binding.viewpager.currentItem - 1

        }
        binding.rightBtn.setOnClickListener {
            binding.viewpager.currentItem = binding.viewpager.currentItem + 1
        }
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }
        viewPager.adapter = adapter
    }
}