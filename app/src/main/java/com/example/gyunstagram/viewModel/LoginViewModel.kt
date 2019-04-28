package com.example.gyunstagram.viewModel

import android.content.Intent
import android.provider.Settings.System.getString
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.util.MainActivityStarterUserCase
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(val starter : MainActivityStarterUserCase) : BaseViewModel(){

    val auth = FirebaseAuth.getInstance()
    /*lateinit var googleSignInClient : GoogleSignInClient
    val GOOGLE_LOGIN_CODE = 9001*/

    var _emailText = MutableLiveData<String>()
    var _passwordText = MutableLiveData<String>()

    fun signInAndSignUp(){
        auth?.let {
            auth.signInWithEmailAndPassword(_emailText.value.toString(), _passwordText.value.toString())?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful){
                    moveMainPage(task.result!!.user)
                }else if (task.exception?.message.isNullOrEmpty()){
                    starter.showToast(task.exception?.message.toString())
                }else {
                    signinEmail()
                }
            }
        }
    }
    fun signinEmail(){
        auth?.let {
            auth.createUserWithEmailAndPassword(_emailText.value.toString(), _passwordText.value.toString())?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful){
                    moveMainPage(task.result!!.user)
                }else {
                    starter.showToast(task.exception?.message.toString())
                }
            }
        }
    }

    /**
    *  구글 로그인은 잠재적 중단. clean 한 architecture 로 가져가기 위해 고민해야할 부분이 있음
    * */
    /*fun google (){
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("120304466811-nodcaekpf3chrlcfrvc4f8l5rtqeqhjm.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(starter.getContext(), gso)
    }

    fun googleLogin(){
        var signInIntent = googleSignInClient?.signInIntent
    }*/

    fun moveMainPage(user: FirebaseUser){
        starter.showMainActivity()

    }


}