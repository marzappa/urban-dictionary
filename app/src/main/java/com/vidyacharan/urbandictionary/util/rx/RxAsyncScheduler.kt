package com.vidyacharan.urbandictionary.util.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class RxAsyncScheduler: AsyncScheduler {
    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}