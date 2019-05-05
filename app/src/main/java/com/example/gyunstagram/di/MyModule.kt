package com.example.gyunstagram.di

import com.example.gyunstagram.usecase.impl.ActivityStarterUseCaseImpl
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.viewModel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var mainActivityStarter = module {
    factory {
        ActivityStarterUseCaseImpl(androidContext())
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

var customDialogPart = module {
    single {
        CustomProgressDialog(androidContext())
    }
}




var myDiModule = listOf(viewModelPart,mainActivityStarter, customDialogPart)