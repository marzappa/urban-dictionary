package com.vidyacharan.urbandictionary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vidyacharan.urbandictionary.data.model.DefinitionData
import com.vidyacharan.urbandictionary.data.repository.DefinitionRepository
import com.vidyacharan.urbandictionary.ui.main.MainViewModel
import com.vidyacharan.urbandictionary.util.NetworkManager
import com.vidyacharan.urbandictionary.util.common.ResultState
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkManager: NetworkManager

    @Mock
    private lateinit var definitionRepository: DefinitionRepository

    @Mock
    private lateinit var messageStringIdObserver: Observer<ResultState<Int>>

    @Mock
    private lateinit var definitionsObserver: Observer<ResultState<List<DefinitionData>>>

    private lateinit var testScheduler: TestScheduler
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        mainViewModel = MainViewModel(
            asyncScheduler = MockAsyncScheduler(testScheduler),
            compositeDisposable = CompositeDisposable(),
            networkManager = networkManager,
            definitionRepository = definitionRepository
        )

        mainViewModel.messageStringId.observeForever(messageStringIdObserver)
        mainViewModel.definitions.observeForever(definitionsObserver)
    }

    @Test
    fun givenServer200_WithValidInput() {

        doReturn(true)
            .`when`(networkManager)
            .isConnected()

        val word = "broship"

        val data = DefinitionData(
            word = word,
            defId = 12345,
            definition = "The state of being \"bro's\" with someone. Extreme love for a bro in a unconditional way.\n",
            example = "My friends and I represent bro ship by smoking blunts together.",
            author = "Fitzgerald",
            writtenOn = "2009-03-28T00:00:00.000Z",
            thumbsDown = 10,
            thumbsUp = 50
        )
        doReturn(Single.just(listOf(data)))
            .`when`(definitionRepository)
            .getDefinitions(word)

        mainViewModel.getDefinitions(word)

        testScheduler.triggerActions()

        assert(mainViewModel.definitions.value == ResultState.success(listOf(data)))
        verify(definitionsObserver).onChanged(ResultState.success(listOf(data)))
    }

    @Test
    fun givenServer200_WithEmptyInput() {

        doReturn(true)
            .`when`(networkManager)
            .isConnected()

        val word = ""

        doReturn(Single.just(emptyList<DefinitionData>()))
            .`when`(definitionRepository)
            .getDefinitions(word)

        mainViewModel.getDefinitions(word)

        testScheduler.triggerActions()

        verify(definitionsObserver).onChanged(ResultState.success(emptyList()))
        assert(mainViewModel.definitions.value == ResultState.success(emptyList<DefinitionData>()))
    }

    @Test
    fun givenNoInternet_whenEmptyInput() {
        doReturn(false)
            .`when`(networkManager)
            .isConnected()

        mainViewModel.getDefinitions("bar")

        assert(mainViewModel.messageStringId.value == ResultState.error(R.string.no_internet_message))
        verify(messageStringIdObserver).onChanged(ResultState.error(R.string.no_internet_message))
    }

    @After
    fun tearDown() {
        mainViewModel.definitions.removeObserver(definitionsObserver)
        mainViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }
}