package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.HorizontalItemBinding
import com.example.linkagelab.databinding.VerticalItemBinding

class HorizontalListAdapter :  RecyclerView.Adapter<HorizontalListAdapter.MyListViewHolder>() {

    var stringList: MutableList<String>? = null

    inner class MyListViewHolder (val binding: HorizontalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.item.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(HorizontalItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
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