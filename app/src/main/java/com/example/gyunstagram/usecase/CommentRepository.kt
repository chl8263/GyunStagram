package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Observable

interface CommentRepository {
    fun getCommentsData () : Observable<ArrayList<ContentDTO.Cooment>>
}