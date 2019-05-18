package com.example.gyunstagram.usecase

import io.reactivex.Observable

interface ProfileImageRepository {
    fun getProfileImage(userUid: String) : Observable<String>
}