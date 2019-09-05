package com.vidyacharan.urbandictionary.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vidyacharan.urbandictionary.DictionaryApplication
import com.vidyacharan.urbandictionary.di.component.ActivityComponent
import com.vidyacharan.urbandictionary.di.component.DaggerActivityComponent
import com.vidyacharan.urbandictionary.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        setupObserver()
        viewModel.onCreate()
    }

    @LayoutRes
    abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)
    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected open fun setupObserver() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(getString(this)) }
        })
    }

    protected fun showMessage(message: String? = "") {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as DictionaryApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

}