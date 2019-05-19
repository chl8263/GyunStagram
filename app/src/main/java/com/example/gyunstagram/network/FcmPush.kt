package com.example.gyunstagram.network

import android.util.Log
import androidx.lifecycle.ReportFragment
import com.example.gyunstagram.vo.FcmDTO
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FcmPush {

    /*var JSON = MediaType.parse("application/json; charset=utf-8")
    var url = "https://fcm.googleapis.com/fcm/send"
    var serverKey = "AAAAHAK0e3s:APA91bF2k-t60NMjDpLSW5-Iusgko27UkE7Nko5n_B5yvpuiS_fbAn1eQixz16gq61Jzk2SZL8ZxCwwXcMnwqbwSSZ3T9c92C41jjW6eLEYqMyXZtGkPECoFlySUE5zNaFDlVnPk22_1"


    var okhttpClient : OkHttpClient ? = null
    var gson : Gson? = null
    init {
        gson = Gson()
        okhttpClient = OkHttpClient()
    }*/
    var fcmService : Api = Retrofit.Builder()
        .baseUrl("https://fcm.googleapis.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)


    fun sendMessage(destinationUid : String , title : String , message : String){
        FirebaseFirestore.getInstance().collection("pushtokens").document(destinationUid).get().addOnCompleteListener {
            task ->
            if(task.isSuccessful) {
                var token = task.result?.get("pushToken").toString()

                var pushDTO = FcmDTO()

                pushDTO.to = token
                pushDTO.notification?.title = title
                pushDTO.notification?.body = message

                Log.e("RetrofitTest" , pushDTO.toString())

                fcmService.sendChatNOtification(pushDTO)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.run {
                            Log.e("RetrofitTest" , it.toString())
                        }
                    },{
                        Log.e("RetrofitTest failed" , it.toString())
                    })



            }
        }
    }
}