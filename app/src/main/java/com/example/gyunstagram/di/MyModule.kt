package com.example.gyunstagram.di

import com.example.gyunstagram.usecase.ActivityStarterUseCase
import com.example.gyunstagram.viewModel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var mainActivityStarter = module {
    factory {
        ActivityStarterUseCase(androidContext())
    }
}

var viewModelPart = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        AddPthotoViewModel()
    }
    viewModel {
        FragmentDetailViewModel()
    }
    viewModel {
        FragmentGridViewModel()
    }
    viewModel {
        FragmentUserViewModel()
    }
    viewModel {
        FragmentAlarmViewModel()
    }
}




var myDiModule = listOf(viewModelPart,mainActivityStarter)