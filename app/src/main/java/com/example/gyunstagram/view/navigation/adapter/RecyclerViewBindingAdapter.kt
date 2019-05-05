package com.example.gyunstagram.view.navigation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gyunstagram.vo.ContentDTO

object RecyclerViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind_item")
    fun bind_item(view : RecyclerView, contentDtoList : ArrayList<ContentDTO> , contentUidList : ArrayList<String>){

        val adapter = view.adapter ?: DetailViewRecyclerViewAdapter(contentDtoList, contentUidList).apply { view.adapter = this }

        adapter.notifyDataSetChanged()
    }

}