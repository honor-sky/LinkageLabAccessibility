package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityViewpagerBinding
import com.google.android.material.tabs.TabLayout

class ViewpagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewpagerBinding

    val frag1 = HomeFragment()
    val frag2 = BookmarkFragment() //RegisterFragment()
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

        binding.dot1.setOnClickListener {
            binding.viewpager.currentItem = 0
        }

        binding.dot2.setOnClickListener {
            binding.viewpager.currentItem = 1
        }

        binding.dot3.setOnClickListener {
            binding.viewpager.currentItem = 2
        }


        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                binding.viewpager.announceForAccessibility("${fragList.size}개의 페이지 중 ${position + 1}번째 페이지")

                when(position) {
                    0 -> {
                        binding.dot1.setBackgroundResource(R.drawable.base_circle_yellow)
                        binding.dot2.setBackgroundResource(R.drawable.base_circle_black)
                        binding.dot3.setBackgroundResource(R.drawable.base_circle_black)
                    }

                    1 -> {
                        binding.dot1.setBackgroundResource(R.drawable.base_circle_black)
                        binding.dot2.setBackgroundResource(R.drawable.base_circle_yellow)
                        binding.dot3.setBackgroundResource(R.drawable.base_circle_black)
                    }

                    2 -> {
                        binding.dot1.setBackgroundResource(R.drawable.base_circle_black)
                        binding.dot2.setBackgroundResource(R.drawable.base_circle_black)
                        binding.dot3.setBackgroundResource(R.drawable.base_circle_yellow)
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

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