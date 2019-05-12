package com.example.gyunstagram.view.navigation.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gyunstagram.vo.ContentDTO
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragmentRecyclerViewadapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var contentDTOs : ArrayList<ContentDTO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class CustomViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView){

    }

}