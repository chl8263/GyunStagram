package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.ProfileImageRepository
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class ProfileImageRepositoryImpl : ProfileImageRepository {

    private val fireStore : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun getProfileImage(): Observable<ArrayList<String>> {
        return Observable.create{
            emitter ->
            fireStore.collection("proFile")
        }
    }
}