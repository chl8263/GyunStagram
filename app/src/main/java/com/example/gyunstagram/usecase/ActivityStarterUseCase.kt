package com.example.gyunstagram.usecase

import android.content.Context
import android.content.Intent
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.MainActivity

class ActivityStarterUseCase (private val context: Context){
    fun showMainActivity(){
        context.startActivity(Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    fun showToast( msg: String){
        context.toast(msg)
    }

    fun getContext() = context
}