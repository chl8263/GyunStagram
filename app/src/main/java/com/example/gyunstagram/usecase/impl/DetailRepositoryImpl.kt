package com.example.gyunstagram.usecase.impl

import android.util.Log
import com.example.gyunstagram.usecase.DetailRepository
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.Single

class DetailRepositoryImpl : DetailRepository {


    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    var contentDtoList = ArrayList<ContentDTO>()
    var contentUidList = ArrayList<String>()

    override fun getDetailVIew(): Single<ArrayList<ContentDTO>> {
        firestore.collection("images").orderBy("timestamp")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->

                for (snapshot in querySnapshot!!.documents) {

                    var items = snapshot.toObject(ContentDTO::class.java)
                    Log.e("aaa",items.toString())
                    contentDtoList.add(items!!)
                    contentUidList.add(snapshot.id)
                }

            }
        return Single.just(contentDtoList)
    }

    override fun freshFavorite(): Single<ArrayList<ContentDTO>> {

    }



}