package com.example.gyunstagram.view

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityMainBinding
import com.example.gyunstagram.view.navigation.*
import com.example.gyunstagram.viewModel.MainViewModel
import com.example.gyunstagram.vo.MessageEvent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val uid : String by lazy { FirebaseAuth.getInstance().currentUser!!.uid}

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

        viewDataBinding.viewModel = viewModel

        viewModel.mNavigationItemSelectedListener = navigationSelectedListener

        //permission setting and request first enter app
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)

        EventBus.getDefault().register(this)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    fun replaceFragment(fragment : Fragment){
        var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_content,fragment).commit()
    }

    var navigationSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            menuItem ->
        setToolbarDefault()
        when(menuItem.itemId){
            R.id.action_home -> {
                replaceFragment(DetailViewFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                replaceFragment(GridFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_add_photo -> {
                //replaceFragment(TwoFragment.newInstance())
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(Intent(this,AddPhotoActivity::class.java))
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_favorite_alarm -> {
                replaceFragment(AlarmFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {
                var fragment = UserFragment.newInstance()

                var bundle = Bundle()
                bundle.putString(UserFragment.DESTINATIONUID , uid)

                fragment.arguments = bundle

                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            else -> false

        }
        return@OnNavigationItemSelectedListener false
    }

    fun setToolbarDefault(){
        toolbar_username.visibility = View.GONE
        toolbar_btn_back.visibility = View.GONE
        toolbar_title_image.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : MessageEvent){
        if(event.eventName == "userFragment"){
            var fragment = UserFragment.newInstance()

            var bundle = Bundle()
            bundle.putString(UserFragment.DESTINATIONUID , event.destinationUid)
            bundle.putString(UserFragment.USERID , event.userId)

            fragment.arguments = bundle
            replaceFragment(fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
