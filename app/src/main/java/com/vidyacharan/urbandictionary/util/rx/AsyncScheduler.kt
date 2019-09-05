package com.vidyacharan.urbandictionary.util.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
interface AsyncScheduler {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun mainThread(): Scheduler
}