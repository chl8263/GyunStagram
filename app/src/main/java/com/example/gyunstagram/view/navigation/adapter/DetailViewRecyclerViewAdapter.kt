package com.example.gyunstagram.view.navigation.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gyunstagram.R
import com.example.gyunstagram.di.mainActivityStarterPart
import com.example.gyunstagram.network.FcmPush
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.Const.FAVORITE_ALARM
import com.example.gyunstagram.view.navigation.UserFragment
import com.example.gyunstagram.vo.AlarmDTO
import com.example.gyunstagram.vo.ContentDTO
import com.example.gyunstagram.vo.MessageComment
import com.example.gyunstagram.vo.MessageEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Single
import kotlinx.android.synthetic.main.item_detail.view.*
import org.greenrobot.eventbus.EventBus


class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var contentDtoList = ArrayList<ContentDTO>()



    private var uid : String? = FirebaseAuth.getInstance().currentUser!!.uid
    private val firestore : FirebaseFirestore? = FirebaseFirestore.getInstance()

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

        //This code is when thw page is loaded
        if(contentDtoList[position].favorite.containsKey(uid)){
            viewHolder.detailViewItem_favorite_imageView.setImageResource(R.drawable.ic_favorite)
        }else{
            viewHolder.detailViewItem_favorite_imageView.setImageResource(R.drawable.ic_favorite_border)
        }

        viewHolder.detailViewItem_favorite_imageView.setOnClickListener {
            favoriteEvent(position)
        }

        viewHolder.detailviewitem_profile_image.setOnClickListener {
           EventBus.getDefault().post(
               MessageEvent(
                   eventName = "userFragment",
                   destinationUid = contentDtoList[position].uid.toString(),
                   userId = contentDtoList[position].userId.toString()))
        }
        viewHolder.detailViewItem_comment_imageView.setOnClickListener {
            EventBus.getDefault().post(
                MessageComment(
                type = "trance_comment_activity",
                contentUid = contentDtoList[position].documentuid.toString() ,
                destinationUid = contentDtoList[position].uid.toString()
            ))
        }

    }

    private fun favoriteEvent(position: Int) {
        var tsDoc = firestore?.collection(Const.FIREBASE_COLLECTION_IMAGES)?.document(contentDtoList[position].documentuid.toString())
        firestore?.runTransaction {transaction ->

            var contentDTO = transaction.get(tsDoc!!).toObject(ContentDTO::class.java)

            if(contentDTO!!.favorite.containsKey(uid)){
                contentDTO?.favoriteCount = contentDTO?.favoriteCount-1
                contentDTO?.favorite.remove(uid)
            }else {
                contentDTO?.favoriteCount =contentDTO?.favoriteCount + 1
                contentDTO?.favorite[uid!!] = true
                favoriteAlarm(contentDtoList[position].uid!!)
            }
            transaction.set(tsDoc,contentDTO)
        }
    }

    fun favoriteAlarm (destinationUid : String){
        var alarmDTO = AlarmDTO(
            destinationUid = destinationUid,
            userId = FirebaseAuth.getInstance().currentUser?.email,
            uid = FirebaseAuth.getInstance().currentUser?.uid,
            kind = FAVORITE_ALARM,
            timestamp = System.currentTimeMillis()
        )
        FirebaseFirestore.getInstance().collection("alarms").document().set(alarmDTO)


    }

    private  inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)
}