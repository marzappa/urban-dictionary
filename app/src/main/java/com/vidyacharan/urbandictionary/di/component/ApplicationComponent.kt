package com.vidyacharan.urbandictionary.di.component

import android.app.Application
import android.content.Context
import com.vidyacharan.urbandictionary.DictionaryApplication
import com.vidyacharan.urbandictionary.data.local.DatabaseService
import com.vidyacharan.urbandictionary.data.repository.DefinitionRepository
import com.vidyacharan.urbandictionary.di.ApplicationContext
import com.vidyacharan.urbandictionary.di.module.ApplicationModule
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: DictionaryApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getCompositeDisposable(): CompositeDisposable

    fun getSchedulerProvider(): AsyncScheduler

    fun getDefinitionRepository(): DefinitionRepository

    fun getNetworkManager(): NetworkManager

    fun getDatabaseService(): DatabaseService
}
