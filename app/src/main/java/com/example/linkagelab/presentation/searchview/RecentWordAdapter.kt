package com.example.linkagelab.presentation.searchview

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.RecentwordItemBinding


class RecentWordAdapter(val searchWord : (String) -> (Unit), val removeWord : (String) -> (Unit)): RecyclerView.Adapter<RecentWordAdapter.RecentWordViewHolder>() {

    var recentSearchedList: MutableList<String>? = null


    inner class RecentWordViewHolder(val binding: RecentwordItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(item: String) {
            binding.recentWordText.text = item

            binding.recentWordItem.setOnClickListener {
                // 결과 검색
                searchWord(item)
            }

            binding.recentWordText.contentDescription = "$item, 버튼"
            ViewCompat.setAccessibilityDelegate(  binding.recentWordText, object : AccessibilityDelegateCompat() {
                override fun onInitializeAccessibilityNodeInfo(
                    v: View,
                    info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(v, info)
                    info.roleDescription = null
                }
            })

            binding.deleteBtn.contentDescription =  "최근 검색어 삭제, 버튼, $item"
            ViewCompat.setAccessibilityDelegate(binding.deleteBtn, object : AccessibilityDelegateCompat() {
                override fun onInitializeAccessibilityNodeInfo(
                    v: View,
                    info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(v, info)
                    info.roleDescription = null
                }
            })
            binding.deleteBtn.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)

            binding.deleteBtn.setOnClickListener {
                removeWord(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentWordViewHolder {
        return RecentWordViewHolder(RecentwordItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }


    override fun onBindViewHolder(holder: RecentWordViewHolder, position: Int) {
        holder.bind(recentSearchedList!![position])
    }


    fun setDate(newList : MutableList<String>) {
        recentSearchedList = newList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return recentSearchedList!!.size
    }


}