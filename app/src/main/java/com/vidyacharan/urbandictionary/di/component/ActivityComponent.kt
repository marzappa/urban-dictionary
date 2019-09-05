package com.vidyacharan.urbandictionary.di.component

import com.vidyacharan.urbandictionary.di.ActivityScope
import com.vidyacharan.urbandictionary.di.module.ActivityModule
import com.vidyacharan.urbandictionary.ui.main.MainActivity
import com.vidyacharan.urbandictionary.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}