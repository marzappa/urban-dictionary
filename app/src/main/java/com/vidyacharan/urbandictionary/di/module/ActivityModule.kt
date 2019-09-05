package com.vidyacharan.urbandictionary.di.module

import androidx.lifecycle.ViewModelProviders
import com.vidyacharan.urbandictionary.data.repository.DefinitionRepository
import com.vidyacharan.urbandictionary.ui.base.BaseActivity
import com.vidyacharan.urbandictionary.ui.main.MainViewModel
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.ViewModelProviderFactory
import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideMainViewModel(
        asyncScheduler: AsyncScheduler,
        compositeDisposable: CompositeDisposable,
        networkManager: NetworkManager,
        definitionRepository: DefinitionRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(asyncScheduler, compositeDisposable, networkManager, definitionRepository)
        }
    ).get(MainViewModel::class.java)
}