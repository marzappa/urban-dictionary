package com.vidyacharan.urbandictionary

import android.app.Application
import com.vidyacharan.urbandictionary.di.component.ApplicationComponent
import com.vidyacharan.urbandictionary.di.component.DaggerApplicationComponent
import com.vidyacharan.urbandictionary.di.module.ApplicationModule
import timber.log.Timber

class DictionaryApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // For Ui testing, used for testing purpose only
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}