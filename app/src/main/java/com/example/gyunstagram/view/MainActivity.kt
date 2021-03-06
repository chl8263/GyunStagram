package com.example.gyunstagram.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityMainBinding
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_PUSHTOKENS
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.navigation.*
import com.example.gyunstagram.viewModel.MainViewModel
import com.example.gyunstagram.vo.MessageEvent
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val uid : String by lazy { FirebaseAuth.getInstance().currentUser!!.uid}

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

        viewDataBinding.viewModel = viewModel

        viewModel.mNavigationItemSelectedListener = navigationSelectedListener

        //permission setting and request first enter app
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)

        EventBus.getDefault().register(this)

        registerPushToken()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    fun registerPushToken(){

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            task ->
            if(!task.isSuccessful){
                this.toast("Firebase token get failed, you can't receive push message")
                return@addOnCompleteListener
            }

            var pushToken = task.result?.token
            var uid = FirebaseAuth.getInstance().currentUser?.uid
            var map = mutableMapOf<String,Any>()

            map["pushToken"] = pushToken!!
            FirebaseFirestore.getInstance().collection(FIREBASE_COLLECTION_PUSHTOKENS).document(uid!!).set(map)
        }

    }

    fun replaceFragment(fragment : Fragment){
        var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_content,fragment).commit()
    }

    var navigationSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            menuItem ->
        setToolbarDefault()
        when(menuItem.itemId){
            R.id.action_home -> {
                replaceFragment(DetailViewFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                replaceFragment(GridFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_add_photo -> {
                //replaceFragment(TwoFragment.newInstance())
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(Intent(this,AddPhotoActivity::class.java))
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_favorite_alarm -> {
                replaceFragment(AlarmFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {
                var fragment = UserFragment.newInstance()

                var bundle = Bundle()
                bundle.putString(UserFragment.DESTINATIONUID , uid)

                fragment.arguments = bundle

                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            else -> false

        }
        return@OnNavigationItemSelectedListener false
    }

    fun setToolbarDefault(){
        toolbar_username.visibility = View.GONE
        toolbar_btn_back.visibility = View.GONE
        toolbar_title_image.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == UserFragment.PICK_PROFILE_FROM_ALBUM && resultCode == Activity.RESULT_OK){
            var imageUri = data?.data
            var uid = FirebaseAuth.getInstance().currentUser?.uid
            var storageRef = FirebaseStorage.getInstance().reference.child(Const.STORAGE_FOLDER_USERPROFILEIMAGES).child(uid!!)
            storageRef.putFile(imageUri!!).continueWithTask { task: Task<UploadTask.TaskSnapshot> ->
                return@continueWithTask storageRef.downloadUrl
            }.addOnSuccessListener {
                uri ->
                var map = HashMap<String , Any>()
                map["image"] = uri.toString()
                FirebaseFirestore.getInstance().collection(Const.FIRESTORE_COLLECTION_PROFILEIMAGE).document(uid).set(map)
            }.addOnFailureListener {
                this.toast("DB error")
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : MessageEvent){
        if(event.eventName == "userFragment"){
            var fragment = UserFragment.newInstance()

            var bundle = Bundle()
            bundle.putString(UserFragment.DESTINATIONUID , event.destinationUid)
            bundle.putString(UserFragment.USERID , event.userId)

            fragment.arguments = bundle
            replaceFragment(fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
