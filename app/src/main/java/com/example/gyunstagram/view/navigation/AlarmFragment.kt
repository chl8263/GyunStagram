package com.example.gyunstagram.view.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.viewModel.FragmentAlarmViewModel
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmFragment : BaseFragment<FragmentDetailBinding,FragmentAlarmViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.fragment_alarm

    override val viewModel: FragmentAlarmViewModel by viewModel()

    companion object {
        fun newInstance(): AlarmFragment {
            val args = Bundle()
            val fragment = AlarmFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {

    }

    override fun initDataBinding(view: View) {

    }

    override fun initAfterBinding(view: View) {

    }
}