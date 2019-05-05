package com.example.gyunstagram.view.navigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gyunstagram.R
import com.example.gyunstagram.vo.ContentDTO

class DetailViewRecyclerViewAdapter(var contentDtoList : ArrayList<ContentDTO>, var contentUidList : ArrayList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentDtoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = (holder as CustomViewHolder).itemView
        viewHolder.detai
    }

    inner class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }
}