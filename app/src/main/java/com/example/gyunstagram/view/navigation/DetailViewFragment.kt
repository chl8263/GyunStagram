package com.example.gyunstagram.view.navigation

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.view.navigation.adapter.DetailViewRecyclerViewAdapter
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.example.gyunstagram.vo.MessageComment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailViewFragment : BaseFragment<FragmentDetailBinding,FragmentDetailViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel: FragmentDetailViewModel by viewModel()

    val adapter : DetailViewRecyclerViewAdapter by inject()

    companion object {
        fun newInstance(): DetailViewFragment {
            val args = Bundle()
            val fragment = DetailViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {
        viewDataBinding.viewModel =  viewModel

        view.detailviewfragment_recyclerview.layoutManager = LinearLayoutManager(context)
        view.detailviewfragment_recyclerview.adapter = adapter

        EventBus.getDefault().register(this)
    }

    override fun initDataBinding(view: View) {
        viewModel.getDetailData()
        viewModel.contentDtoList.observe(this, Observer {
            /*if(adapter == null){
                view.detailviewfragment_recyclerview.adapter = DetailViewRecyclerViewAdapter(it as ArrayList<ContentDTO>)
            }*/
            adapter?.contentDtoList = it as ArrayList<ContentDTO>
            adapter?.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding(view: View) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : MessageComment){

        if(event.type == "trance_comment_activity"){
            var intent = Intent(context,CommentActivity::class.java)
            intent.putExtra(CommentActivity.COONTENTUID, event.contentUid)
            context?.startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}