package com.example.gyunstagram.view.navigation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gyunstagram.vo.ContentDTO

object UserRecyclerViewBindignAdapter {

    @JvmStatic
    @BindingAdapter("user_recyclerview_bind_item")
    fun user_recyclerview_bind_item(view : RecyclerView, list : ArrayList<ContentDTO>){

        view?.run {
            (adapter!! as UserFragmentRecyclerViewadapter).contentDTOs = list
            adapter!!.notifyDataSetChanged()
        }

    }
}