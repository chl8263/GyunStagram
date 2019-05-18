package com.example.gyunstagram.view.navigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gyunstagram.R
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.Const.COMMENT_ALARM
import com.example.gyunstagram.util.Const.FAVORITE_ALARM
import com.example.gyunstagram.util.Const.FOLLOW_ALARM
import com.example.gyunstagram.vo.AlarmDTO
import kotlinx.android.synthetic.main.item_comment.view.*

class AlarmRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var alarmList: ArrayList<AlarmDTO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = alarmList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val commentTextView = holder.itemView.commentViewItem_textView_profile

        if (alarmList.size != 0) {
            when (alarmList[position].kind) {
                FOLLOW_ALARM -> {
                    val str = alarmList[position].userId + " Liked the press"
                    commentTextView.text = str
                }
                COMMENT_ALARM -> {
                    val str =
                        alarmList[position].userId + " Liked the press " + alarmList[position].message + " left a message"
                    commentTextView.text = str
                }
                FAVORITE_ALARM -> {
                    val str = alarmList[position].userId + " has started to follow your account"
                    commentTextView.text = str
                }
            }
        }
    }

    private inner class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}