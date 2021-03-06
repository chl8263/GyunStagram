package com.example.gyunstagram.usecase.impl

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.example.gyunstagram.usecase.DetailRepository
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.Single

class DetailRepositoryImpl : DetailRepository {

    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private var contentDtoList : ArrayList<ContentDTO> = ArrayList<ContentDTO>()

    override fun getDetailVIew() : Observable<ArrayList<ContentDTO>>{
        return Observable.create {
            emitter ->
            firestore.collection(Const.FIREBASE_COLLECTION_IMAGES).orderBy("timestamp")
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->

                    contentDtoList.clear()
                    if(querySnapshot != null) {
                        for ((i, snapshot) in querySnapshot!!.documents.withIndex()) {

                            var items = snapshot.toObject(ContentDTO::class.java)
                            contentDtoList.add(items!!)
                            contentDtoList[i].documentuid = snapshot.id
                        }
                        emitter.onNext(contentDtoList)
                    }
                }

        }

    }



}