package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Single

interface DetailRepository {

    fun getDetailVIew() : Single<ArrayList<ContentDTO>>
}