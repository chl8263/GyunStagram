package com.example.gyunstagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.AlarmRepositoryImpl
import com.example.gyunstagram.vo.AlarmDTO
import io.reactivex.android.schedulers.AndroidSchedulers

class FragmentAlarmViewModel (val repository: AlarmRepositoryImpl) : BaseViewModel() {

    private val _alarmData = MutableLiveData<ArrayList<AlarmDTO>>()

    val alarmData : LiveData<ArrayList<AlarmDTO>>
        get() = _alarmData

    fun getAlarmData(){
        addDisposable(
            repository.getAlarmData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {t: ArrayList<AlarmDTO>? ->

                    _alarmData.postValue(t)

                }
        )
    }
}