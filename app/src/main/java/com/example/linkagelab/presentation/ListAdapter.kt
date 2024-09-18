package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.ListItemBinding


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyReviewViewHolder>() {

    var stringList: MutableList<String>? = null

    inner class MyReviewViewHolder (val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        return MyReviewViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
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