package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.CommentRepository
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_IMAGES
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class CommentRepositoryImpl : CommentRepository {

    val firestore : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }


    override fun getCommentsData(): Observable<ArrayList<ContentDTO.Cooment>> = Observable.create {
        emitter ->
        /*firestore.collection(FIREBASE_COLLECTION_IMAGES)
            .document(conetne)*/
    }

}