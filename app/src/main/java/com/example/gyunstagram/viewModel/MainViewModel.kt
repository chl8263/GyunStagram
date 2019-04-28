package com.example.gyunstagram.viewModel

import com.example.gyunstagram.core.BaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainViewModel : BaseViewModel(){
    lateinit var mNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener

    fun setNavigationItemSelectedListener (mNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener) {
        this.mNavigationItemSelectedListener = mNavigationItemSelectedListener
    }
}