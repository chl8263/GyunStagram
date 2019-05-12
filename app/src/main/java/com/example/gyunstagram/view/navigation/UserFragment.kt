package com.example.gyunstagram.view.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.databinding.FragmentUserBinding
import com.example.gyunstagram.view.navigation.adapter.UserFragmentRecyclerViewadapter
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import com.example.gyunstagram.viewModel.FragmentUserViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.fragment_user.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment<FragmentUserBinding,FragmentUserViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.fragment_user

    override val viewModel: FragmentUserViewModel by viewModel()

    val adapter : UserFragmentRecyclerViewadapter by inject()

    companion object {

        val destinationUID = "destinationUid"

        fun newInstance(): UserFragment {
            val args = Bundle()
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {

        viewDataBinding.viewModel = viewModel

        adapter.resources =resources
        view.account_recyclerView.adapter = adapter
        view.account_recyclerView.layoutManager = GridLayoutManager(activity,3)



    }

    override fun initDataBinding(view: View) {
        viewModel.getAccountViewData()

        viewModel.userLiveData.observe(this, Observer {
            adapter.contentDTOs = it as ArrayList<ContentDTO>
            adapter.notifyDataSetChanged()
        })

        viewModel.account_tv_post_count.observe(this, Observer {
            if(it == null) view.account_tv_post_count.text = "0"

            view.account_tv_post_count.text = it

        })
    }

    override fun initAfterBinding(view: View) {

    }
}