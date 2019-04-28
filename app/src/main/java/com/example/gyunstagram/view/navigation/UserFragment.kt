package com.example.gyunstagram.view.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import com.example.gyunstagram.viewModel.FragmentUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment<FragmentDetailBinding,FragmentUserViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.fragment_user

    override val viewModel: FragmentUserViewModel by viewModel()

    companion object {
        fun newInstance(): UserFragment {
            val args = Bundle()
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(viwe: View) {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}