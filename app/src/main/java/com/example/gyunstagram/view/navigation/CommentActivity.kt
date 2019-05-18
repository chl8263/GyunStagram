package com.example.gyunstagram.view.navigation

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityCommentBinding
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_COMMENTS
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_IMAGES
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.navigation.adapter.CommentRecyclerViewAdapter
import com.example.gyunstagram.viewModel.CommentViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_comment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CommentActivity : BaseActivity<ActivityCommentBinding,CommentViewModel>() {

    lateinit var contentUid : String

    override val layoutResourceId: Int
        get() = R.layout.activity_comment

    override val viewModel: CommentViewModel by viewModel()

    val adapter : CommentRecyclerViewAdapter by inject()

    companion object {
        val COONTENTUID = "COONTENTUID"
    }

    override fun initStartView() {

        contentUid = intent.getStringExtra(CommentActivity.COONTENTUID)

        viewDataBinding.viewModel = viewModel


        commentRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        commentRecyclerView.adapter = adapter

        comment_btn_send.setOnClickListener {

            var comment  = ContentDTO.Comment()
            comment.userId = FirebaseAuth.getInstance().currentUser?.email
            comment.uid = FirebaseAuth.getInstance().currentUser?.uid
            comment.comment = comment_edit_message.text.toString()
            comment.timestamp = System.currentTimeMillis()

            // DB 에 Comment 쌓기
            FirebaseFirestore.getInstance().collection(FIREBASE_COLLECTION_IMAGES).document(contentUid).collection(FIREBASE_COLLECTION_COMMENTS).document().set(comment).addOnCompleteListener {
                task ->
                if(task.isCanceled) this.toast("DB error")
            }

            comment_edit_message.setText("")
        }


    }

    override fun initDataBinding() {
        viewModel.getCommentsData(contentUid)

        viewModel.commentsData.observe(this, Observer {
            adapter.comments = it as ArrayList<ContentDTO.Comment>
            adapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {

    }

}
