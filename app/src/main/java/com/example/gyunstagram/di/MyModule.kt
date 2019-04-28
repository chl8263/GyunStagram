package com.example.gyunstagram.di

import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.util.MainActivityStarterUserCase
import com.example.gyunstagram.viewModel.LoginViewModel
import com.example.gyunstagram.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module


var mainActivityStarter = module {
    factory {
        MainActivityStarterUserCase(androidContext())
    }
}

var viewModelPart = module {
    viewModel {
        LoginViewModel(get())
    }
}




var myDiModule = listOf(viewModelPart,mainActivityStarter)