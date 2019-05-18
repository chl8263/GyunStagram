package com.example.gyunstagram.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gyunstagram.core.BaseViewModel
import com.example.gyunstagram.usecase.impl.AccountRepositoryImpl
import com.example.gyunstagram.usecase.impl.ProfileImageRepositoryImpl
import com.example.gyunstagram.usecase.impl.RequestFollowRepositoryImpl
import com.example.gyunstagram.vo.ContentDTO
import com.example.gyunstagram.vo.FollowDTO
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentUserViewModel(
    private val accountRepository: AccountRepositoryImpl,
    private val profileRepository: ProfileImageRepositoryImpl,
    private val followRepository: RequestFollowRepositoryImpl
) : BaseViewModel() {

    private val _userLiveData = MutableLiveData<ArrayList<ContentDTO>>()
    val userLiveData: LiveData<ArrayList<ContentDTO>>
        get() = _userLiveData

    private val _account_tv_post_count = MutableLiveData<String>()
    val account_tv_post_count: LiveData<String>
        get() = _account_tv_post_count

    private val _userProfileUri = MutableLiveData<String>()
    val userProfileUri: LiveData<String>
        get() = _userProfileUri

    private val _followAndFollowing = MutableLiveData<FollowDTO>()
    val followAndFollowing: LiveData<FollowDTO>
        get() = _followAndFollowing

    fun getAccountViewData(userUid: String) {
        addDisposable(
            accountRepository.getAccountViewData(userUid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: ArrayList<ContentDTO>? ->
                    _userLiveData.postValue(t)
                    _account_tv_post_count.postValue(t?.size.toString())
                }
        )
    }

    fun getUserProfileUri(userUid: String) {
        addDisposable(
            profileRepository.getProfileImage(userUid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: String? ->
                    _userProfileUri.postValue(t)
                }
        )
    }

    fun requestFollow(destinationUid: String) {
        followRepository.getRequestFolow(destinationUid)
    }

    fun getFollowAndFollowing(uid: String) {
        addDisposable(
            followRepository.getFollowerAndFollowing(uid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: FollowDTO? ->
                    _followAndFollowing.postValue(t)
                }
        )
    }


}