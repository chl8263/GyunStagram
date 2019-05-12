package com.example.gyunstagram.view.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.gyunstagram.R
import com.example.gyunstagram.core.BaseActivity
import com.example.gyunstagram.databinding.ActivityAddPhotoBinding
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.toast
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.viewModel.AddPthotoViewModel
import com.example.gyunstagram.viewModel.LoginViewModel
import com.example.gyunstagram.vo.ContentDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add_photo.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : BaseActivity<ActivityAddPhotoBinding,AddPthotoViewModel>() {


    private lateinit var storage : FirebaseStorage
    private lateinit var photoUri : Uri
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private val customProgressDialog : CustomProgressDialog by inject()

    override val layoutResourceId: Int
        get() = R.layout.activity_add_photo

    override val viewModel : AddPthotoViewModel by viewModel()

    companion object {
        val PICK_IMAGE_FROM_ALBUM = 0
    }

    override fun initStartView() {

        //initiate storage
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        //open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)

        //add image upload event
        photo_btn_upload.setOnClickListener {
            contentUpload()
        }

    }


    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == Activity.RESULT_OK){
                // This is path to the selected image
                photoUri = data?.data!!
                addPhoto_image.setImageURI(photoUri)
            }else {
                //exit the addPhotoActivity if you leave album without selecting it
                finish()
            }
        }
    }
    fun contentUpload(){

        //customProgressDialog.show()

        //Make fileName

        var timestamp = SimpleDateFormat("yyyy.MMdd_HHmmss").format(Date())
        var imageFIleName = "IMAGE_${timestamp}_.png"

        var storageRef = storage.reference.child(Const.FIREBASE_COLLECTION_IMAGES).child(imageFIleName)

        //Promise method
        storageRef.putFile(photoUri).continueWithTask { task: Task<UploadTask.TaskSnapshot> ->
            return@continueWithTask storageRef.downloadUrl
        }.addOnSuccessListener { uri->

            //customProgressDialog.dismiss()

            var contentDTO = ContentDTO()

            //Insert file information
            contentDTO.imageUrl = uri.toString()
            contentDTO.uid = auth.currentUser?.uid
            contentDTO.userId = auth.currentUser?.email
            contentDTO.explain = addphoto_edit_explain.text.toString()
            contentDTO.timestamp = System.currentTimeMillis()
            firestore.collection(Const.FIREBASE_COLLECTION_IMAGES).document().set(contentDTO)

            setResult(Activity.RESULT_OK)

            finish()
        }/*.addOnFailureListener {
            customProgressDialog.dismiss()
        }*/


    }
}
