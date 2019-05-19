package com.example.gyunstagram.network

import com.example.gyunstagram.vo.FcmDTO
import com.example.gyunstagram.vo.FcmResponseDTO
import com.squareup.okhttp.Response
import com.squareup.okhttp.ResponseBody
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    /*@Headers("Authorization: key=AAAAHAK0e3s:APA91bF2k-t60NMjDpLSW5-Iusgko27UkE7Nko5n_B5yvpuiS_fbAn1eQixz16gq61Jzk2SZL8ZxCwwXcMnwqbwSSZ3T9c92C41jjW6eLEYqMyXZtGkPECoFlySUE5zNaFDlVnPk22_1",
        "Content-Type:application/json")*/
    @Headers(value = ["Authorization: key=AAAAHAK0e3s:APA91bF2k-t60NMjDpLSW5-Iusgko27UkE7Nko5n_B5yvpuiS_fbAn1eQixz16gq61Jzk2SZL8ZxCwwXcMnwqbwSSZ3T9c92C41jjW6eLEYqMyXZtGkPECoFlySUE5zNaFDlVnPk22_1",
        "Content-Type:application/json"])
    @POST("fcm/send")
    fun sendChatNOtification (@Body body: FcmDTO) : Single<FcmResponseDTO>
}