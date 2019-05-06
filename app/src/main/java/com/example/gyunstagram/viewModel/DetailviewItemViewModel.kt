package com.example.gyunstagram.viewModel

import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.DetailRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailviewItemViewModel(private val repository : DetailRepositoryImpl,private val parent : FragmentDetailViewModel) : BaseViewModel() {

    fun freshFavorite(){
        addDisposable(repository.freshFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t: ArrayList<ContentDTO>? ->
                parent.refreshFavorite(t as ArrayList<ContentDTO>)
            }
        )
    }

}