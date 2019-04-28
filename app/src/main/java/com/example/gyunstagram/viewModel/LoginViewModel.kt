package com.example.gyunstagram.viewModel

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.util.MainActivityStarterUserCase
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(val starter : MainActivityStarterUserCase) : BaseViewModel(){

    val auth = FirebaseAuth.getInstance()

    var _emailText = MutableLiveData<String>()
    var _passwordText = MutableLiveData<String>()

    fun signInAndSignUp(){
        auth?.let {
            Log.e("c",_emailText.value.toString())
            auth.createUserWithEmailAndPassword(_emailText.value.toString(), _passwordText.value.toString())?.addOnCompleteListener {
                    task ->
                Log.e("a","a2")
                if(task.isSuccessful){
                    Log.e("a","a")
                    moveMainPage(task.result.user)
                }else if (task.exception?.message.isNullOrEmpty()){
                    Log.e("12",task.exception?.message.toString())
                    starter.showToast(task.exception?.message.toString())
                }else {
                    Log.e("a","a2")
                }
            }
        }
    }

    fun moveMainPage(user: FirebaseUser){
        starter.showMainActivity()

    }


}