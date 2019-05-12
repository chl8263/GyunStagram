package com.example.gyunstagram.di

import com.example.gyunstagram.usecase.impl.AccountRepositoryImpl
import com.example.gyunstagram.usecase.impl.ActivityStarterUseCaseImpl
import com.example.gyunstagram.usecase.impl.DetailRepositoryImpl
import com.example.gyunstagram.usecase.impl.ProfileImageRepositoryImpl
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.view.navigation.adapter.DetailViewRecyclerViewAdapter
import com.example.gyunstagram.view.navigation.adapter.GridFragmentRecyclerViewAdapter
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
    factory {
        ProfileImageRepositoryImpl()
    }

}

var recyclerViewAdapterPart = module {
    single {
        DetailViewRecyclerViewAdapter()
    }
    single {
        UserFragmentRecyclerViewadapter()
    }
    single {
        GridFragmentRecyclerViewAdapter()
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
        FragmentGridViewModel(get())
    }
    viewModel {
        FragmentUserViewModel(get(),get())
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