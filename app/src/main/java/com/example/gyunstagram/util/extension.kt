package com.example.gyunstagram.util

import android.content.Context
import android.widget.Toast


fun Context.toast(msg : CharSequence) =
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()