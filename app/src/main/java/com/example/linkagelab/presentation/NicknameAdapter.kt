package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.NicknameItemBinding


class NicknameAdapter : RecyclerView.Adapter<NicknameAdapter.NicknameViewHolder>() {

    lateinit var kakaoKrewList: MutableList<String>
    var searchKeyword = ""

    inner class NicknameViewHolder(val binding: NicknameItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.nicknameTextView.text = item

            if(searchKeyword != "" && item.contains(searchKeyword)) {
                binding.nicknameTextView.setBackgroundResource(R.color.kakao_yellow)
            } else {
                binding.nicknameTextView.setBackgroundResource(R.color.white)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NicknameViewHolder {
        return NicknameViewHolder(NicknameItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }


    override fun onBindViewHolder(holder: NicknameViewHolder, position: Int) {
        holder.bind(kakaoKrewList[position])
    }


    fun setDate(newList : MutableList<String>) {
        kakaoKrewList = newList
        notifyDataSetChanged()
    }

    fun setKeyword(new_keyword : String) {
        searchKeyword = new_keyword
    }


    override fun getItemCount(): Int {
        return kakaoKrewList.size
    }
}