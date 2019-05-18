package com.example.gyunstagram.viewModel

import androidx.databinding.ObservableField
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.CommentRepositoryImpl

class CommentViewModel(val commentRepositoryImpl: CommentRepositoryImpl) : BaseViewModel() {

    lateinit var contentUid : String



}