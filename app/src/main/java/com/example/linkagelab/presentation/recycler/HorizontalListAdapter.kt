package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.MusicItemBinding

class HorizontalListAdapter :  RecyclerView.Adapter<HorizontalListAdapter.MyListViewHolder>() {

    var stringList: MutableList<String>? = null

    inner class MyListViewHolder (val binding:MusicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.item.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(MusicItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
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