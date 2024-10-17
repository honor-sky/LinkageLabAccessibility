package com.example.linkagelab.presentation.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.linkagelab.databinding.MusicItemAccesibilityBinding

class HorizontalListCustomAdapter :  RecyclerView.Adapter<HorizontalListCustomAdapter.MyListViewHolder>() {

    var stringList: MutableList<String>? = null

    inner class MyListViewHolder (val binding:MusicItemAccesibilityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.musicTitle.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(MusicItemAccesibilityBinding.inflate(LayoutInflater.from(parent.context),parent, false))
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