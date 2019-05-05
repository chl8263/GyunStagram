package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.DetailRepository
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.FirebaseFirestore

class DetailRepositoryImpl : DetailRepository{

    val firestore : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    lateinit var contentDtoList : ArrayList<ContentDTO>
    lateinit var contentUidList : ArrayList<String>

    override fun getDetailVIew() {
        firestore.collection("images").orderBy("timestamp").addSnapshotListener{querySnapshot , firebaseFirestoreException->

            contentDtoList.clear()
            contentUidList.clear()
            for(snapshot in querySnapshot!!.documents){
                var items = snapshot.toObject(ContentDTO::class.java)
                contentDtoList.add(items!!)
            }
        }
    }
}