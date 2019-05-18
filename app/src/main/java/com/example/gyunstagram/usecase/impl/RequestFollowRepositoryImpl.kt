package com.example.gyunstagram.usecase.impl

import com.example.gyunstagram.usecase.RequestFollowRepository
import com.example.gyunstagram.util.Const
import com.example.gyunstagram.util.Const.FOLLOW_ALARM
import com.example.gyunstagram.vo.AlarmDTO
import com.example.gyunstagram.vo.FollowDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class RequestFollowRepositoryImpl : RequestFollowRepository {

    private val fireStore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val myUid: String by lazy { FirebaseAuth.getInstance().currentUser!!.uid }

    override fun getRequestFolow(destinationUid: String) {
        // DB의 내 저장소에 갱신할 데이터
        var tsDocFollowing = fireStore.collection("users").document(myUid)
        fireStore.runTransaction { transaction ->
            var followDTO = transaction.get(tsDocFollowing).toObject(FollowDTO::class.java)

            if (followDTO == null) {
                followDTO = FollowDTO()
                followDTO.followingCount = 1
                followDTO.followers[destinationUid] = true

                transaction.set(tsDocFollowing, followDTO)
                return@runTransaction
            }
            if (followDTO.followings.containsKey(destinationUid)) {
                followDTO.followingCount = followDTO.followingCount - 1
                followDTO.followers.remove(destinationUid)
            } else {
                followDTO.followingCount = followDTO.followingCount + 1
                followDTO.followers[destinationUid] = true
                followerAlarm(destinationUid)
            }
            transaction.set(tsDocFollowing, followDTO)
            return@runTransaction
        }

        // DB의 제 3자의 정보를 갱신하는 로직
        var tsDocFollower = fireStore.collection("users").document(destinationUid)
        fireStore.runTransaction { transaction ->
            var followDTO = transaction.get(tsDocFollower).toObject(FollowDTO::class.java)
            if (followDTO == null) {
                followDTO = FollowDTO()
                followDTO!!.followerCount = 1
                followDTO!!.followers[myUid] = true

                transaction.set(tsDocFollower, followDTO!!)
                return@runTransaction
            }

            if (followDTO!!.followers.containsKey(myUid)) {
                followDTO!!.followerCount = followDTO!!.followerCount - 1
                followDTO!!.followers.remove(myUid)
            } else {
                followDTO!!.followerCount = followDTO!!.followerCount + 1
                followDTO!!.followers[myUid] = true
                followerAlarm(destinationUid)
            }
            transaction.set(tsDocFollower, followDTO!!)
            return@runTransaction
        }
    }

    override fun getFollowerAndFollowing(uid: String): Observable<FollowDTO> {
        return Observable.create {
                emitter ->
            fireStore.collection("users").document(uid).addSnapshotListener{documentSnapshot, firebaseFirestoreException ->
                documentSnapshot?.let {
                    var followDTO = documentSnapshot.toObject(FollowDTO::class.java)
                    emitter.onNext(followDTO!!)
                }
            }

        }
    }

    fun followerAlarm (destinationUid : String){
        var alarmDTO = AlarmDTO(
            destinationUid = destinationUid,
            userId = FirebaseAuth.getInstance().currentUser?.email,
            uid = FirebaseAuth.getInstance().currentUser?.uid,
            kind = FOLLOW_ALARM,
            timestamp = System.currentTimeMillis()
        )
        FirebaseFirestore.getInstance().collection("alarms").document().set(alarmDTO)
    }
}
