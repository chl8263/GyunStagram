package com.example.gyunstagram.view.navigation

import androidx.fragment.app.Fragment
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailViewFragment : BaseFragment<FragmentDetailBinding,FragmentDetailViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail
    override val viewModel: FragmentDetailViewModel by viewModel()


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