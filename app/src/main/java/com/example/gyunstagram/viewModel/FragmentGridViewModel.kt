package com.example.gyunstagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.AccountRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.android.schedulers.AndroidSchedulers

class FragmentGridViewModel(private val repository: AccountRepositoryImpl) : BaseViewModel() {

    private val _userLiveData = MutableLiveData<ArrayList<ContentDTO>>()

    val userLiveData: LiveData<ArrayList<ContentDTO>>
        get() = _userLiveData

    fun getAccountViewData(userUid: String) {
        addDisposable(
            repository.getAccountViewData(userUid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: ArrayList<ContentDTO>? ->
                    _userLiveData.postValue(t)
                }
        )
    }
}