package com.example.gyunstagram.usecase

import com.example.gyunstagram.vo.AlarmDTO
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Observable

interface AlarmRepository {
    fun getAlarmData () : Observable<ArrayList<AlarmDTO>>
}