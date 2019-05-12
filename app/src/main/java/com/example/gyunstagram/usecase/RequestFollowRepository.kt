package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.FollowDTO
import io.reactivex.Observable

interface RequestFollowRepository {

    fun getRequestFolow( destinationUid : String)

    fun getFollowerAndFollowing(uid : String) : Observable<FollowDTO>
}