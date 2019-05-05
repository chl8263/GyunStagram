package com.example.gyunstagram.view.navigation

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentDetailBinding
import com.example.gyunstagram.viewModel.FragmentDetailViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailViewFragment : BaseFragment<FragmentDetailBinding,FragmentDetailViewModel>(){

    val firestore : FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override val viewModel: FragmentDetailViewModel by viewModel()

    companion object {
        fun newInstance(): DetailViewFragment {
            val args = Bundle()
            val fragment = DetailViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(viwe: View) {
        viewDataBinding.viewModel =  viewModel

        detailviewfragment_recyclerview.layoutManager = LinearLayoutManager(context)

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}