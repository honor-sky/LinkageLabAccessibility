package com.example.linkagelab.presentation.recycler

import android.text.TextUtils
import android.util.Log
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
            //binding.contentTitle.ellipsize = TextUtils.TruncateAt.END
            binding.contentDetail.text = item.content


            // 말줄임 발생하면, 줄여진것 제외하고 설정
            binding.contentTitle.post {
                if (binding.contentTitle.layout != null) {
                    if(binding.contentTitle.layout.getEllipsisCount(binding.contentTitle.getLineCount() - 1) > 0) {
                        binding.contentTitle.contentDescription = "${item.title.substring(
                            0,
                            item.title.length - binding.contentTitle.layout.getEllipsisCount(binding.contentTitle.getLineCount() - 1)
                        )} 말줄임"
                    } else {
                        binding.contentTitle.contentDescription = item.title
                    }
                }
            }
            binding.contentDetail.post{
                if (binding.contentDetail.layout != null) {
                    if(binding.contentDetail.layout.getEllipsisCount(binding.contentDetail.getLineCount() - 1) > 0) {
                        binding.contentDetail.contentDescription = "${item.content.substring(
                            0,
                            item.content.length - binding.contentDetail.layout.getEllipsisCount(binding.contentDetail.getLineCount() - 1)
                        )} 말줄임"
                    } else {
                        binding.contentDetail.contentDescription = item.content
                    }
                }
            }

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