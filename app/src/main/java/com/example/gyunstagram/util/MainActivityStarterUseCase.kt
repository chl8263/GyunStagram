package com.example.gyunstagram.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.gyunstagram.view.MainActivity

class MainActivityStarterUseCase (private val context: Context){
    fun showMainActivity(){
        context.startActivity(Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    fun showToast( msg: String){
        context.toast(msg)
    }

    fun getContext() = context
}