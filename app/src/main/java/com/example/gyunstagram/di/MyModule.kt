package com.example.gyunstagram.di

import com.example.gyunstagram.usecase.impl.AccountRepositoryImpl
import com.example.gyunstagram.usecase.impl.ActivityStarterUseCaseImpl
import com.example.gyunstagram.usecase.impl.DetailRepositoryImpl
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.view.navigation.adapter.DetailViewRecyclerViewAdapter
import com.example.gyunstagram.view.navigation.adapter.UserFragmentRecyclerViewadapter
import com.example.gyunstagram.viewModel.*
import com.example.gyunstagram.vo.ContentDTO
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var mainActivityStarterPart = module {
    factory {
        ActivityStarterUseCaseImpl(androidContext())
    }
}

var repositoryPart = module {
    factory {
        DetailRepositoryImpl()
    }
    factory {
        AccountRepositoryImpl()
    }
}

var recyclerViewAdapterPart = module {
    single {
        DetailViewRecyclerViewAdapter()
    }
    single {
        UserFragmentRecyclerViewadapter()
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
        FragmentDetailViewModel(get())
    }
    viewModel {
        FragmentGridViewModel()
    }
    viewModel {
        FragmentUserViewModel(get())
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

var myDiModule = listOf(
    viewModelPart,mainActivityStarterPart, customDialogPart,repositoryPart,recyclerViewAdapterPart
    )