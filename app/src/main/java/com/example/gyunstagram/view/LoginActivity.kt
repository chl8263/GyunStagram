package com.example.gyunstagram.view

import android.util.Log
import androidx.lifecycle.Observer
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity  : BaseActivity<com.example.gyunstagram.databinding.ActivityLoginBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login

    override val viewModel : LoginViewModel by viewModel()

    override fun initStartView() {
        viewDataBinding.viewModel = viewModel

    }

    override fun initDataBinding() {
        viewModel.isMoveMainPage.observe(this, Observer {
            finish()
        })
    }

    override fun initAfterBinding() {
    }

}
