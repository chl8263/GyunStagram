package com.example.gyunstagram.view.navigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gyunstagram.R
import com.example.gyunstagram.vo.ContentDTO
import kotlinx.android.synthetic.main.item_detail.view.*

class DetailViewRecyclerViewAdapter(var contentDtoList : ArrayList<ContentDTO>/*, var contentUidList : ArrayList<String>*/)
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

        //userId
        viewHolder.detailViewItem_profile_text.text = contentDtoList[position].userId

        //Image
        Glide.with(holder.itemView.context).load(contentDtoList[position].imageUrl).into(viewHolder.detailViewItem_imageView_content)

        //Explain
        viewHolder.detailViewItem_explain_textView.text = contentDtoList[position].explain

        //like
        viewHolder.detailViewItem_favoriteCounter_textView.text = "Likes ${contentDtoList[position].favoriteCount}"


    }

    inner class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }
}