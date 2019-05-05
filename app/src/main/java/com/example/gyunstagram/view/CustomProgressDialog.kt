package com.example.gyunstagram.view

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.animation.AnimationUtils
import com.example.gyunstagram.R
import kotlinx.android.synthetic.main.activity_progress.*

class CustomProgressDialog(context : Context) : ProgressDialog(context){

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        getWindow().setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT));

        progressActivity_imageView.animation = AnimationUtils.loadAnimation(context,R.anim.loading)

    }

    override fun show() {
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
    }
}