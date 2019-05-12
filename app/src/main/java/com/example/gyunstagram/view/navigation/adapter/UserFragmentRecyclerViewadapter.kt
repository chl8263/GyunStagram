package com.example.gyunstagram.view.navigation.adapter

import android.content.res.Resources
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gyunstagram.vo.ContentDTO
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragmentRecyclerViewadapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var contentDTOs : ArrayList<ContentDTO> = arrayListOf()

    lateinit var resources : Resources

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var width = resources.displayMetrics.widthPixels / 3

        var imageView = ImageView(parent.context)
        imageView.layoutParams = LinearLayoutCompat.LayoutParams(width,width)

        return CustomViewHolder(imageView)
    }

    override fun getItemCount(): Int = contentDTOs.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var imageView = (holder as CustomViewHolder).imageView

        Glide.with(holder.itemView.context).load(contentDTOs[position].imageUrl).apply(RequestOptions().centerCrop()).into(imageView)
    }

    inner class CustomViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView){

    }

}