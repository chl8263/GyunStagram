package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.CommentRepository
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_COMMENTS
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_IMAGES
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class CommentRepositoryImpl : CommentRepository {

    var comments = arrayListOf<ContentDTO.Comment>()

    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }


    override fun getCommentsData(contentUid: String): Observable<ArrayList<ContentDTO.Comment>> {

        return Observable.create { emitter ->
            firestore.collection(FIREBASE_COLLECTION_IMAGES)
                .document(contentUid)
                .collection(FIREBASE_COLLECTION_COMMENTS)
                .orderBy("timestamp")
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    comments.clear()

                    if(querySnapshot == null) return@addSnapshotListener

                    for(snapshot in querySnapshot!!.documents){
                        comments.add(snapshot.toObject(ContentDTO.Comment::class.java)!!)
                    }

                    emitter.onNext(comments)

                }
        }
    }

}