package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.AlarmRepository
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_ALARMS
import com.example.gyunstagram.util.Const.FIREBASE_COLLECTION_COMMENTS
import com.example.gyunstagram.vo.AlarmDTO
import com.example.gyunstagram.vo.ContentDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class AlarmRepositoryImpl : AlarmRepository{

    var alarmList = ArrayList<AlarmDTO>()

    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val myUid : String by lazy { FirebaseAuth.getInstance().currentUser!!.uid}

    override fun getAlarmData(): Observable<ArrayList<AlarmDTO>> {

        alarmList.clear()

        return Observable.create { emitter ->
            firestore.collection(FIREBASE_COLLECTION_ALARMS)
                .whereEqualTo("destinationUid",myUid)
                .orderBy("timestamp")
                .addSnapshotListener{querySnapshot, firebaseFirestoreException ->
                    if(querySnapshot == null) return@addSnapshotListener

                    for(snapshot in querySnapshot.documents){
                        alarmList.add((snapshot.toObject(AlarmDTO::class.java)!!))
                    }

                    emitter.onNext(alarmList)

                }
        }
    }

}