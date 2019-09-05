package com.vidyacharan.urbandictionary.di.component

import android.content.Context
import com.vidyacharan.urbandictionary.di.ApplicationContext
import com.vidyacharan.urbandictionary.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
    //fixes compiler issue
    @ApplicationContext
    override fun getContext(): Context {
        return getApplication()
    }
}
