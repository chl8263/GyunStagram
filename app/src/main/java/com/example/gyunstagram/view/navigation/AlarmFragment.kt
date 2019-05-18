package com.example.gyunstagram.view.navigation

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.view.navigation.adapter.AlarmRecyclerViewAdapter
import com.example.gyunstagram.view.navigation.adapter.CommentRecyclerViewAdapter
import com.example.gyunstagram.viewModel.FragmentAlarmViewModel
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmFragment : BaseFragment<FragmentDetailBinding,FragmentAlarmViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.fragment_alarm

    override val viewModel: FragmentAlarmViewModel by viewModel()

    val adapter : AlarmRecyclerViewAdapter by inject()

    companion object {
        fun newInstance(): AlarmFragment {
            val args = Bundle()
            val fragment = AlarmFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {
        view.alarmfragment_recycleView.adapter = adapter
        view.alarmfragment_recycleView.layoutManager = LinearLayoutManager(context)
    }

    override fun initDataBinding(view: View) {
        viewModel.getAlarmData()

        viewModel.alarmData.observe(this, Observer {
            adapter.alarmList = it
            adapter.notifyDataSetChanged()
        })

    }

    override fun initAfterBinding(view: View) {

    }
}