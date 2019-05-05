package com.example.gyunstagram.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.vo.ContentDTO

class FragmentDetailViewModel : BaseViewModel() {

    val contentDtoList = ObservableArrayList<ContentDTO>()
    val contentUidList = ObservableArrayList<String>()



}