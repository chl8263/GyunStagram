package com.example.gyunstagram.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.CommentRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import io.reactivex.android.schedulers.AndroidSchedulers

class CommentViewModel(private val repository: CommentRepositoryImpl) : BaseViewModel() {


    private val _commentsData = MutableLiveData<ArrayList<ContentDTO.Comment>>()

    val commentsData : LiveData<ArrayList<ContentDTO.Comment>>
        get() = _commentsData

    fun getCommentsData(contentUid : String){
        addDisposable(
            repository.getCommentsData(contentUid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{t: ArrayList<ContentDTO.Comment>? ->

                }

        )
    }

}