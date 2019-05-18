package com.example.gyunstagram.di

import com.example.gyunstagram.usecase.impl.*
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.view.navigation.adapter.*
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
    factory {
        RequestFollowRepositoryImpl()
    }
    factory {
        CommentRepositoryImpl()
    }
    factory {
        AlarmRepositoryImpl()
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
    single {
        CommentRecyclerViewAdapter()
    }
    single {
        AlarmRecyclerViewAdapter()
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
        FragmentUserViewModel(get(),get(),get())
    }
    viewModel {
        FragmentAlarmViewModel(get())
    }
    viewModel {
        CommentViewModel(get())
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