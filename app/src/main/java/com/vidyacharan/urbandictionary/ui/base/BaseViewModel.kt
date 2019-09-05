package com.vidyacharan.urbandictionary.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vidyacharan.urbandictionary.R
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.common.ResultState
import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val asyncScheduler: AsyncScheduler,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkManager: NetworkManager
) : ViewModel() {

    val messageStringId: MutableLiveData<ResultState<Int>> = MutableLiveData()
    val messageString: MutableLiveData<ResultState<String>> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun checkInternet(): Boolean = networkManager.isConnected()

    protected fun checkInternetWithMessage(): Boolean =
        if (networkManager.isConnected()) {
            true
        } else {
            messageStringId.postValue(ResultState.error(R.string.no_internet_message))
            false
        }

    abstract fun onCreate()
}