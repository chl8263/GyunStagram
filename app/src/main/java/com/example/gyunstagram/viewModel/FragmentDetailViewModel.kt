package com.example.gyunstagram.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.DetailRepository
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentDetailViewModel(private val repository : DetailRepository) : BaseViewModel() {

    var contentDtoList = ObservableArrayList<ContentDTO>()
    val contentUidList = ObservableArrayList<String>()

    fun getDetailData(){
        addDisposable(repository.getDetailVIew()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1: ArrayList<ContentDTO>?, t2: Throwable? ->
                contentDtoList = t1 as ObservableArrayList<ContentDTO>
            })
    }

}