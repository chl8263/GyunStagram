package com.example.gyunstagram.usecase

import io.reactivex.Observable

interface RequestFollowRepository {

    fun getRequestFolow( destinationUid : String)
}