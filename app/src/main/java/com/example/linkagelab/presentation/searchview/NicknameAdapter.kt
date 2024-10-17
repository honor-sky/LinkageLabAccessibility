package com.example.linkagelab.presentation

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.set
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.R
import com.example.linkagelab.databinding.NicknameItemBinding


class NicknameAdapter(val saveWord : (String) -> (Unit)) : RecyclerView.Adapter<NicknameAdapter.NicknameViewHolder>() {

    lateinit var kakaoKrewList: MutableList<String>
    var searchKeyword = ""


    inner class NicknameViewHolder(val binding: NicknameItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(item: String) {
            binding.nicknameTextView.text = item

            if(searchKeyword != "" && item.contains(searchKeyword)) {

                val start_idx = item.indexOf(searchKeyword)
                val end_idx = start_idx + searchKeyword.length

                val spannableString = SpannableString(item)
                spannableString.setSpan(BackgroundColorSpan(R.color.kakao_yellow),
                    start_idx,
                    end_idx,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.nicknameTextView.text = spannableString


            } else {

                binding.nicknameTextView.setBackgroundResource(R.color.white)

            }


            binding.nicknameTextView.setOnClickListener {
                saveWord(item)
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