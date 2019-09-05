package com.vidyacharan.urbandictionary.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.vidyacharan.urbandictionary.BuildConfig
import com.vidyacharan.urbandictionary.DictionaryApplication
import com.vidyacharan.urbandictionary.data.local.DatabaseService
import com.vidyacharan.urbandictionary.data.remote.NetworkService
import com.vidyacharan.urbandictionary.data.remote.Networking
import com.vidyacharan.urbandictionary.di.ApplicationContext
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import com.vidyacharan.urbandictionary.util.rx.RxAsyncScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationTestModule(private val application: DictionaryApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): AsyncScheduler = RxAsyncScheduler()

    @Singleton
    @Provides
    fun provideNetworkManager(): NetworkManager = NetworkManager(application)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService = Networking.create(
        apiKey = BuildConfig.API_KEY,
        apiHost = BuildConfig.BASE_URL.removePrefix("https://"),
        baseUrl = BuildConfig.BASE_URL,
        cacheDir = application.cacheDir,
        cacheSize = 5 * 1024 * 1024L
    )

    @Singleton
    @Provides
    fun provideDatabaseService(): DatabaseService = Room.databaseBuilder(
        application,
        DatabaseService::class.java,
        "urban_dictionary_db_test"
    ).build()
}