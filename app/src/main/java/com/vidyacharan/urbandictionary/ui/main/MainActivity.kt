package com.vidyacharan.urbandictionary.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.vidyacharan.urbandictionary.R
import com.vidyacharan.urbandictionary.data.model.DefinitionData
import com.vidyacharan.urbandictionary.di.component.ActivityComponent
import com.vidyacharan.urbandictionary.ui.base.BaseActivity
import com.vidyacharan.urbandictionary.ui.common.hideKeyboard
import com.vidyacharan.urbandictionary.util.common.ResultState
import com.vidyacharan.urbandictionary.util.common.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private val definitionAdapter by lazy { DefinitionAdapter() }
    private val definitions = arrayListOf<DefinitionData>()

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupSearchView()
        setupAdapter()
        setupThumbUp()
        setupThumbDown()
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.definitions.observe(this, Observer { resultState ->
            if (resultState.status == Status.SUCCESS) {
                gotDefinitions(resultState.data)
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupSearchView() {
        searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_button)?.apply {
            setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white))
        }

        searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)?.apply {
            setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white))
        }

        searchView?.findViewById<SearchView.SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)?.apply {
            setHintTextColor(ContextCompat.getColor(this@MainActivity, R.color.lightGray))
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                progress.visibility = View.VISIBLE
                viewModel.getDefinitions(query)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        container.setOnTouchListener { _, _ ->
            hideKeyboard()
            return@setOnTouchListener true
        }

    }

    private fun setupAdapter() {
        definitionList?.apply {
            setHasFixedSize(true)
            adapter = definitionAdapter
        }
    }

    private fun setupThumbUp() {
        thumbUp.setOnClickListener {
            definitions.toMutableList().apply {
                sortByDescending { upSelector(it) }
                setAdapterData(this)
            }
        }
    }

    private fun setupThumbDown() {
        thumbDown.setOnClickListener {
            val data = definitions.toMutableList()
            data.sortByDescending { downSelector(it) }
            setAdapterData(data)
        }
    }

    private fun upSelector(definitionData: DefinitionData): Int = definitionData.thumbsUp
    private fun downSelector(definitionData: DefinitionData): Int = definitionData.thumbsDown

    private fun gotDefinitions(data: List<DefinitionData>?) {
        progress.visibility = View.GONE

        if (data.isNullOrEmpty()) {
            emptyData.visibility = View.VISIBLE
            emptyData.text = getString(R.string.empty_result_text)
            return
        }

        thumbUp.visibility = View.VISIBLE
        thumbDown.visibility = View.VISIBLE

        definitions.clear()
        definitions.addAll(data)

        setAdapterData(data)
    }

    private fun setAdapterData(data: List<DefinitionData>) {
        definitionList.visibility = View.VISIBLE
        definitionAdapter.setData(data)
    }
}
