package com.example.gyunstagram.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityCommentBinding
import com.example.gyunstagram.viewModel.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CommentActivity : BaseActivity<ActivityCommentBinding,CommentViewModel>() {


    override val layoutResourceId: Int
        get() = R.layout.activity_comment

    override val viewModel: CommentViewModel by viewModel()

    companion object {
        val COONTENTUID = "COONTENTUID"
    }

    override fun initStartView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initDataBinding() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initAfterBinding() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
