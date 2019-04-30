package com.example.gyunstagram.view.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityAddPhotoBinding
import com.example.gyunstagram.viewModel.AddPthotoViewModel
import com.example.gyunstagram.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPhotoActivity : BaseActivity<ActivityAddPhotoBinding,AddPthotoViewModel>() {


    override val layoutResourceId: Int
        get() = R.layout.activity_add_photo

    override val viewModel : AddPthotoViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}
