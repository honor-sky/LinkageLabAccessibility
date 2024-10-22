package com.example.linkagelab.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.ContentItemBinding
import com.example.linkagelab.databinding.VerticalItemBinding


class VerticalListAdapter : RecyclerView.Adapter<VerticalListAdapter.MyListViewHolder>() {

    var contentList: MutableList<BrunchContent> = mutableListOf()

    inner class MyListViewHolder (val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BrunchContent) {
            binding.contentTitle.text = item.title
            binding.contentDetail.text = item.content

            binding.contentTitle.contentDescription = (if(item.title.length > 20) "${item.title.subSequence(0,20)} 말줄임" else item.title).toString()
            binding.contentDetail.contentDescription = (if(item.content.length > 50) "${item.content.subSequence(0,50)} 말줄임" else item.content).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(ContentItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        if (contentList != null) {
            holder.bind(contentList!![position])
        }
    }

    override fun getItemCount() = contentList?.size ?: 0

    fun setData(contentList: MutableList<BrunchContent>){
        this.contentList= contentList
        notifyDataSetChanged()
    }

    fun addItems(newItems: MutableList<BrunchContent>) {
        contentList = (contentList?.plus(newItems))?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

}