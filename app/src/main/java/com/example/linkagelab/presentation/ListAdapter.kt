package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.VerticalItemBinding


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyReviewViewHolder>() {

    var stringList: MutableList<String>? = null

    inner class MyReviewViewHolder (val binding: VerticalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.item.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        return MyReviewViewHolder(VerticalItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MyReviewViewHolder, position: Int) {
        if (stringList != null) {
            holder.bind(stringList!![position])
        }
    }

    override fun getItemCount() = stringList?.size ?: 0

    fun setData(reviewList: MutableList<String>){
        this.stringList= reviewList
        notifyDataSetChanged()
    }



}