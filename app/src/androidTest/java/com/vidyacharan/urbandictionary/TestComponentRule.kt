package com.vidyacharan.urbandictionary

import android.content.Context
import com.vidyacharan.urbandictionary.di.component.DaggerTestComponent
import com.vidyacharan.urbandictionary.di.component.TestComponent
import com.vidyacharan.urbandictionary.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {

    private var testComponent: TestComponent? = null

    fun getContext() = context

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDaggerApplicationComponent()
                    base.evaluate()
                } finally {
                    testComponent = null
                }
            }

        }
    }

    private fun setupDaggerApplicationComponent() {
        val application = context.applicationContext as DictionaryApplication

        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        application.setComponent(testComponent!!)
    }
}