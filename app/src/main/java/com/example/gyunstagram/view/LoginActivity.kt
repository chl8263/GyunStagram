package com.example.gyunstagram.view

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
    }

    override fun initAfterBinding() {
    }

    override fun onStart() {
        super.onStart()
        viewModel.moveMainPage()
    }
}
