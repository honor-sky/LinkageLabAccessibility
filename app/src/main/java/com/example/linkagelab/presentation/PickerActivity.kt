package com.example.linkagelab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.linkagelab.R
import com.example.linkagelab.databinding.ActivityPickerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator

class PickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPickerBinding

    val frag1 = TimePickerFrag()
    val frag2 = DatePickerFrag()
    val frag3 = CalendarFrag()
    val fragList = arrayOf(frag1, frag2, frag3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager(binding.viewpager, binding.tabLayout)

        binding.backBtn.setOnClickListener {
            finish()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->

            when(position) {
                0 -> tab.text = "TimePicker"
                1 -> tab.text = "DatePicker"
                2 -> tab.text = "Calendar"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: Tab?) {
            }

            override fun onTabReselected(tab: Tab?) {
            }
        })

    }

    private fun setupViewPager(viewPager: ViewPager2, tabLayout: TabLayout) {
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