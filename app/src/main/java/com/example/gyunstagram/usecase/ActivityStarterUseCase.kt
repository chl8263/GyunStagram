package com.example.gyunstagram.usecase

import android.content.Context
import android.widget.Toast

interface ActivityStarterUseCase {

    fun showMainActivity()

    fun showToast( msg: String)

    fun getContext() : Context
}