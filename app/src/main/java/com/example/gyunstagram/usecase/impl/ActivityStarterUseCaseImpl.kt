package com.example.gyunstagram.usecase.impl

import android.content.Context
import android.content.Intent
import com.example.gyunstagram.usecase.ActivityStarterUseCase
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.MainActivity

class ActivityStarterUseCaseImpl (private val context: Context) : ActivityStarterUseCase{

    override fun showMainActivity(){
        context.startActivity(Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    override fun showToast(msg: String){
        context.toast(msg)
    }

    override fun getContext() = context
}