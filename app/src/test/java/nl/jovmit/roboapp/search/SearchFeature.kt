package nl.jovmit.roboapp.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class SearchFeature {

  private val showLoading = SearchState.ShowLoading
  private val hideLoading = SearchState.HideLoading

  private lateinit var uiController: SpyUiController

  @BeforeEach
  fun setUp() {
    val validator = Validator()
    val repository = SearchRepository()
    val searcher = Searcher(validator, repository)
    uiController = SpyUiController().also {
      it.searcher = searcher
    }
    uiController.onCreate()
  }

  @Test
  fun performSearch() {
    val query = "item"
    val matches = listOf("item 1", "another ITEM", "Item 2")

    uiController.submit(query)

    val matchingResults = SearchState.MatchingResults(matches)
    val expectedRenderedStates =
      listOf(showLoading, matchingResults, hideLoading)
    assertEquals(expectedRenderedStates, uiController.renderedStates)
  }

  class SpyUiController : LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry

    val renderedStates = mutableListOf<SearchState>()

    lateinit var searcher: Searcher

    fun submit(query: String) {
      searcher.search(query)
    }

    fun onCreate() {
      lifecycleRegistry = LifecycleRegistry(this)
      lifecycleRegistry.currentState = Lifecycle.State.STARTED
      searcher.searchLiveData.observe(this) {
        renderedStates.add(it)
      }
    }

    override fun getLifecycle(): Lifecycle {
      return lifecycleRegistry
    }
  }
}
