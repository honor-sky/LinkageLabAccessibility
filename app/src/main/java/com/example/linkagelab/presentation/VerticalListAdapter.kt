package com.example.linkagelab.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.VerticalItemBinding


class VerticalListAdapter : RecyclerView.Adapter<VerticalListAdapter.MyListViewHolder>() {

    var stringList: MutableList<String> = mutableListOf()

    inner class MyListViewHolder (val binding: VerticalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.item.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(VerticalItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
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

    fun addItems(newItems: MutableList<String>) {
        stringList = (stringList?.plus(newItems))?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

}