package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.ProfileImageRepository
import com.example.gyunstagram.util.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class ProfileImageRepositoryImpl : ProfileImageRepository {

    private val fireStore : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val uid : String by lazy { FirebaseAuth.getInstance().currentUser!!.uid}

    override fun getProfileImage(): Observable<String> {
        return Observable.create{
            emitter ->
            fireStore.collection(Const.FIRESTORE_COLLECTION_PROFILEIMAGE).document(uid).addSnapshotListener{documentSnapshot, firebaseFirestoreException ->
                if (documentSnapshot == null) return@addSnapshotListener

                documentSnapshot.data?.let {
                    var url = documentSnapshot.data!!["image"].toString()

                    emitter.onNext(url)
                }
            }
        }
    }
}