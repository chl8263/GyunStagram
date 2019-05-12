package com.example.gyunstagram.usecase

import io.reactivex.Observable

interface ProfileImageRepository {
    fun getProfileImage() : Observable<ArrayList<String>>
}