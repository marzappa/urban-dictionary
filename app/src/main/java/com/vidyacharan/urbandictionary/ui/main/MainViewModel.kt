package com.vidyacharan.urbandictionary.ui.main

import androidx.lifecycle.MutableLiveData
import com.vidyacharan.urbandictionary.data.model.DefinitionData
import com.vidyacharan.urbandictionary.data.repository.DefinitionRepository
import com.vidyacharan.urbandictionary.ui.base.BaseViewModel
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.common.ResultState
import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class MainViewModel(
    asyncScheduler: AsyncScheduler,
    compositeDisposable: CompositeDisposable,
    networkManager: NetworkManager,
    private val definitionRepository: DefinitionRepository
) : BaseViewModel(
    compositeDisposable = compositeDisposable,
    asyncScheduler = asyncScheduler,
    networkManager = networkManager
) {

    val definitions: MutableLiveData<ResultState<List<DefinitionData>>> = MutableLiveData()

    override fun onCreate() {}

    internal fun getDefinitions(term: String) {
        if (checkInternetWithMessage()) {
            compositeDisposable.addAll(
                definitionRepository.getDefinitions(term)
                    .subscribeOn(asyncScheduler.io())
                    .subscribe(
                        { response ->

                            definitions.postValue(ResultState.success(response))
                            Timber.d("MainViewModel Success Response $response")
                        },
                        {
                            Timber.d("MainViewModel Error Response \n ${it.localizedMessage}")
                            messageString.postValue(ResultState.error("Something went wrong. Please try again later."))
                        }
                    )
            )
        }

    }
}