package com.example.gyunstagram.view.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseFragment
import com.example.gyunstagram.databinding.FragmentUserBinding
import com.example.gyunstagram.view.LoginActivity
import com.example.gyunstagram.view.MainActivity
import com.example.gyunstagram.view.navigation.AddPhotoActivity.Companion.PICK_IMAGE_FROM_ALBUM
import com.example.gyunstagram.view.navigation.adapter.UserFragmentRecyclerViewadapter
import com.example.gyunstagram.viewModel.FragmentUserViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment<FragmentUserBinding, FragmentUserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_user

    override val viewModel: FragmentUserViewModel by viewModel()

    val adapter: UserFragmentRecyclerViewadapter by inject()

    private val myUid: String by lazy { FirebaseAuth.getInstance().currentUser?.uid.toString() }
    private lateinit var destinationUid: String

    companion object {

        var PICK_PROFILE_FROM_ALBUM = 10

        val DESTINATIONUID = "DESTINATIONUID"
        val USERID = "USERID"

        fun newInstance(): UserFragment {
            val args = Bundle()
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initStartView(view: View) {
        viewDataBinding.viewModel = viewModel

        adapter.resources = resources
        view.account_recyclerView.adapter = adapter
        view.account_recyclerView.layoutManager = GridLayoutManager(activity, 3)

        destinationUid = arguments!!.get(UserFragment.DESTINATIONUID).toString()

        view.account_iv_profile.setOnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            activity?.startActivityForResult(photoPickerIntent, PICK_PROFILE_FROM_ALBUM)
        }

    }

    override fun initDataBinding(view: View) {
        viewModel.getAccountViewData(destinationUid)
        viewModel.getUserProfileUri()

        viewModel.userLiveData.observe(this, Observer {
            adapter.contentDTOs = it as ArrayList<ContentDTO>
            adapter.notifyDataSetChanged()
        })

        viewModel.account_tv_post_count.observe(this, Observer {
            if (it == null) view.account_tv_post_count.text = "0"

            view.account_tv_post_count.text = it

        })

        viewModel.userProfileUri.observe(this, Observer {
            Log.e("aasdsdsd",it)
            Glide.with(activity!!).load(it).apply(RequestOptions().circleCrop()).into(view.account_iv_profile)
        })
    }

    override fun initAfterBinding(view: View) {
        if (myUid == destinationUid) {    //myPage
            view.account_btn_follow_signOut.text = getString(R.string.signout)
            view.account_btn_follow_signOut.setOnClickListener {

                startActivity(Intent(activity, LoginActivity::class.java))
                FirebaseAuth.getInstance().signOut()
                activity?.finish()
            }
        } else {     //other Page
            view.account_btn_follow_signOut.text = getString(R.string.follow)
            var mainActivity = (activity as MainActivity)
            mainActivity.toolbar_username.text = arguments?.getString(UserFragment.USERID)
            mainActivity.toolbar_btn_back.setOnClickListener {
                mainActivity.bottom_navigation.selectedItemId = R.id.action_home
            }
            mainActivity.toolbar_title_image.visibility = View.GONE
            mainActivity.toolbar_username.visibility = View.VISIBLE
            mainActivity.toolbar_btn_back.visibility = View.VISIBLE
        }
    }
}