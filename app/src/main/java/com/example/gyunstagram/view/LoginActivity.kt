package com.example.gyunstagram.view

import android.content.Intent
import android.os.Bundle
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityMainBinding
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.viewModel.LoginViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityMainBinding , LoginViewModel>() {

    lateinit var auth : FirebaseAuth

    override val layoutResourceId: Int
        get() = R.layout.activity_login

    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        email_login_button.setOnClickListener {
            signinAndSingUp()
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    fun signinAndSingUp(){
        auth?.let {
            auth.createUserWithEmailAndPassword(email_editText.text.toString(), password_EditText.text.toString())?.addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    moveMainPage(task.result.user)
                }else if (task.exception?.message.isNullOrEmpty()){
                    this.toast(task.exception?.message.toString())
                }else {

                }
            }
        }
    }

    fun signinEmail(){
        auth?.let {
            auth.signInWithEmailAndPassword(email_editText.text.toString(), password_EditText.text.toString())?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    moveMainPage(task.result.user)
                }else {
                    this.toast(task.exception?.message.toString())
                }
            }
        }
    }

    fun moveMainPage(user: FirebaseUser){
        user?.let { startActivity(Intent(this,MainActivity::class.java)) }
    }
}
