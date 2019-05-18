package com.example.gyunstagram.view.navigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gyunstagram.R
import com.example.gyunstagram.util.Const.FIRESTORE_COLLECTION_PROFILEIMAGE
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var comments : ArrayList<ContentDTO.Comment> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var view = holder.itemView

        view.commentViewItem_textView_comment.text = comments[position].comment
        view.commentViewItem_textView_profile.text = comments[position].userId

        FirebaseFirestore.getInstance()
            .collection(FIRESTORE_COLLECTION_PROFILEIMAGE)
            .document(comments[position].uid!!)
            .get()
            .addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    var url = task.result!!["image"]
                    Glide.with(holder.itemView.context).load(url).apply(RequestOptions.circleCropTransform()).into(view.commentViewItem_imageview_profile)
                }
            }
    }

    inner class CustomViewHolder(var view : View) : RecyclerView.ViewHolder(view)
}