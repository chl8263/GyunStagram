package com.example.gyunstagram.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.AccountRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentUserViewModel(private val repository : AccountRepositoryImpl) : BaseViewModel() {

    private val _userLiveData  = MutableLiveData<ArrayList<ContentDTO>>()

    val userLiveData : LiveData<ArrayList<ContentDTO>>
        get() = _userLiveData

    private val _account_tv_post_count  = MutableLiveData<String>()

    val account_tv_post_count : LiveData<String>
        get() = _account_tv_post_count

    fun getAccountViewData(){
        addDisposable(
            repository.getAccountViewData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    t: ArrayList<ContentDTO>? ->
                    _userLiveData.postValue(t)
                    _account_tv_post_count.postValue(t?.size.toString())
                }
        )
    }

}