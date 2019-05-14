package com.example.gyunstagram.viewModel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.DetailRepository
import com.example.gyunstagram.usecase.impl.DetailRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentDetailViewModel(private val repository : DetailRepositoryImpl) : BaseViewModel() {

    private val _contentDtoList = MutableLiveData<ArrayList<ContentDTO>>()

    val contentDtoList : LiveData<ArrayList<ContentDTO>>
        get() = _contentDtoList

    fun getDetailData(){
        addDisposable(
            repository.getDetailVIew()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t1: ArrayList<ContentDTO>?->
                    _contentDtoList.postValue(t1)
                }
        )
    }


}