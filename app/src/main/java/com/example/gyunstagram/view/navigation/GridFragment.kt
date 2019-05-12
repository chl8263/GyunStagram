package com.example.gyunstagram.view.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.databinding.FragmentGridBinding
import com.example.gyunstagram.view.navigation.adapter.GridFragmentRecyclerViewAdapter
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import com.example.gyunstagram.viewModel.FragmentGridViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_grid.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GridFragment : BaseFragment<FragmentGridBinding,FragmentGridViewModel>(){

    private val myUid : String by lazy { FirebaseAuth.getInstance().currentUser?.uid.toString()}


    override val layoutResourceId: Int
        get() = R.layout.fragment_grid

    override val viewModel: FragmentGridViewModel by viewModel()

    val adapter : GridFragmentRecyclerViewAdapter by inject()

    companion object {
        fun newInstance(): GridFragment {
            val args = Bundle()
            val fragment = GridFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {

        viewDataBinding.viewModel = viewModel

        adapter.resources = resources

        view.gridFragmentRecyclerView.adapter = adapter
        view.gridFragmentRecyclerView.layoutManager = GridLayoutManager(activity,3)


    }

    override fun initDataBinding(view: View) {
        viewModel.getAccountViewData(myUid)

        viewModel.userLiveData.observe(this, Observer {
            adapter.contentDTOs = it as ArrayList<ContentDTO>
            adapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding(view: View) {

    }
}