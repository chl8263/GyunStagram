package com.example.gyunstagram.di

import com.example.gyunstagram.network.Api
import com.example.gyunstagram.network.FcmPush
import com.example.gyunstagram.usecase.impl.*
import com.example.gyunstagram.view.CustomProgressDialog
import com.example.gyunstagram.view.navigation.adapter.*
import com.example.gyunstagram.viewModel.*
import com.example.gyunstagram.vo.ContentDTO
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


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
    single {
        FcmPush()
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

var retrofitPart = module {
    single(named("FcmService")) {
        Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/fcm/send")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}

var customDialogPart = module {
    single {
        CustomProgressDialog(androidContext())
    }
}

var myDiModule = listOf(
    viewModelPart,mainActivityStarterPart, customDialogPart,repositoryPart,recyclerViewAdapterPart,retrofitPart
    )