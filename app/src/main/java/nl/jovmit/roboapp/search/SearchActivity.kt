package nl.jovmit.roboapp.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_search.*
import nl.jovmit.roboapp.R
import nl.jovmit.roboapp.search.data.SearchState

class SearchActivity : AppCompatActivity() {
  private val allItems = listOf(
    "item",
    "Another Item",
    "keyboard",
    "monitor",
    "android",
    "nuts",
    "glasses",
    "beer",
    "computer item",
    "lights",
    "microphone",
    "door",
    "floor",
    "items"
  )

  private val searchService = InMemorySearchService(allItems)
  private val validator = Validator()
  private val repository = SearchRepository(searchService)
  private val searcher = Searcher(validator, repository)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    setupSearchView()
    observeSearchStates()
  }

  private fun observeSearchStates() {
    searcher.searchLiveData.observe(this) {
      when (it) {
        is SearchState.ShowLoading -> toggleLoading(true)
        is SearchState.HideLoading -> toggleLoading(false)
        is SearchState.MatchingResults -> displayMatches(it.matches)
        is SearchState.SearchError -> displayError("Error making search")
        is SearchState.InvalidQuery -> displayError("The query is not valid")
        is SearchState.Offline -> displayError("Offline")
      }
    }
  }

  private fun toggleLoading(show: Boolean) {
    val visibility = if (show) View.VISIBLE else View.GONE
    loadingIndicator.visibility = visibility
  }

  private fun displayMatches(matches: List<String>) {
    val result = if (matches.isEmpty()) "No matches" else
      matches.joinToString(separator = "\n\n")
    resultsView.text = result
  }

  private fun displayError(error: String) {
    resultsView.text = error
  }

  private fun setupSearchView() {
    searchView.setIconifiedByDefault(false)
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { searcher.search(it) }
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }
    })
  }
}
