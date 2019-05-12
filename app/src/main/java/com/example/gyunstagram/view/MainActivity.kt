package com.example.gyunstagram.view

import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityMainBinding
import com.example.gyunstagram.view.navigation.*
import com.example.gyunstagram.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.jar.Manifest

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

        viewDataBinding.viewModel = viewModel

        viewModel.mNavigationItemSelectedListener = navigationSelectedListener

        //permission setting and request first enter app
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
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
                replaceFragment(UserFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }

            else -> false

        }
        return@OnNavigationItemSelectedListener false
    }
}
