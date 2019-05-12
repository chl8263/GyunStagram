package com.example.gyunstagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.vo.ContentDTO

class FragmentUserViewModel : BaseViewModel() {

    private val _userLiveData  = MutableLiveData<ArrayList<ContentDTO>>()

    val userLiveData : LiveData<ArrayList<ContentDTO>>
        get() = _userLiveData


}