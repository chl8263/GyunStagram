package com.example.gyunstagram.view.navigation

import android.view.View
import androidx.fragment.app.Fragment
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GridFragment : BaseFragment<FragmentDetailBinding,FragmentDetailViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel: FragmentDetailViewModel by viewModel()

    override fun initStartView(viwe: View) {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}