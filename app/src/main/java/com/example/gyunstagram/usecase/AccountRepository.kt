package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Observable
import kotlin.collections.ArrayList

interface AccountRepository  {

    fun getAccountViewData() : Observable<ArrayList<ContentDTO>>
}