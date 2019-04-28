package com.example.gyunstagram.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.util.MainActivityStarterUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(val starter : MainActivityStarterUseCase) : BaseViewModel(){

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