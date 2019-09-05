package com.vidyacharan.urbandictionary

import com.vidyacharan.urbandictionary.util.rx.AsyncScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class MockAsyncScheduler(private val testScheduler: TestScheduler) : AsyncScheduler {
    override fun computation(): Scheduler = testScheduler

    override fun io(): Scheduler = testScheduler

    override fun mainThread() = testScheduler
}