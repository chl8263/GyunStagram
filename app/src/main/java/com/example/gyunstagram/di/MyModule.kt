package com.example.gyunstagram.di

import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.viewModel.LoginViewModel
import com.example.gyunstagram.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module


var viewModelPart = module {
    viewModel {
        BaseViewModel()
    }
}

var myDiModule = listOf(viewModelPart)