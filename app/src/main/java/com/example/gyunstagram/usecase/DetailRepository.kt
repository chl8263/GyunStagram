package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Observable
import io.reactivex.Single

interface DetailRepository {

    fun getDetailVIew() : Observable<ArrayList<ContentDTO>>
}