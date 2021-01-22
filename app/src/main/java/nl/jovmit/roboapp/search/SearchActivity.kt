package nl.jovmit.roboapp.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_search.*
import nl.jovmit.roboapp.R
import nl.jovmit.roboapp.search.data.SearchState
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchActivity : AppCompatActivity() {

  private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
  private val searchApi = retrofit.create(SearchApi::class.java)
  private val searchService = RemoteSearchService(searchApi)
  private val repository = Repository(searchService)
  private val validator = QueryValidator(2)
  private val searcher = Searcher(validator, repository)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    setupSearchView()
    observeSearchStates()
  }

  private fun setupSearchView() {
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

  private fun observeSearchStates() {
    searcher.searchStateLiveData.observe(this) {
      when (it) {
        is SearchState.Matches -> displayMatches(it.matches)
        is SearchState.NoMatchFor -> displayNoMatchesFor(it.query)
        is SearchState.Offline -> displayOfflineError()
        is SearchState.BadSearch -> displayBadSearchError()
        is SearchState.BadQuery -> displayBadQueryError()
      }
    }
  }

  private fun displayMatches(matches: List<String>) {
    resultView.text = matches.toString()
  }

  private fun displayNoMatchesFor(query: String) {
    resultView.text = "No matches found for: $query"
  }

  private fun displayOfflineError() {
    resultView.text = "You seem to be offline"
  }

  private fun displayBadSearchError() {
    resultView.text = "Error performing the search"
  }

  private fun displayBadQueryError() {
    resultView.text = "Invalid query"
  }
}