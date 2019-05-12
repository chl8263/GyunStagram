package com.example.gyunstagram.usecase.impl

import android.util.Log
import com.example.gyunstagram.usecase.AccountRepository
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class AccountRepositoryImpl : AccountRepository {

    val firestore : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    val uid : String by lazy { FirebaseAuth.getInstance().currentUser!!.uid}

    var contentDTOList : ArrayList<ContentDTO> = ArrayList<ContentDTO>()

    override fun getAccountViewData(userUid : String): Observable<ArrayList<ContentDTO>> {
        return Observable.create {
            emitter ->
            Log.e("asas",uid)
            firestore.collection(Const.FIREBASE_COLLECTION_IMAGES)?.whereEqualTo("uid",userUid).addSnapshotListener{
                querySnapshot, firebaseFirestoreException ->

                contentDTOList.clear()

                //sometimes, This code return null of querySnapshot when it signout
                if(querySnapshot == null) return@addSnapshotListener

                //getData
                for(snapshot in querySnapshot.documents){
                    contentDTOList.add(snapshot.toObject(ContentDTO::class.java)!!)
                }

                emitter.onNext(contentDTOList)
            }
        }
    }
}