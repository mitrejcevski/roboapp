package nl.jovmit.roboapp.search

import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher {

  private val searchStateLiveData =
    MutableLiveData<SearchState>()

  fun search(query: String) {
    val isValid = query.trim().length > 3
    if (!isValid) {
      searchStateLiveData.value = SearchState.BadQuery
    } else {
      if (query == "item") {
        searchStateLiveData.value = SearchState.Match("Item 1")
      } else if (query == "another") {
        searchStateLiveData.value = SearchState.Match("Another Value")
      } else {
        searchStateLiveData.value = SearchState.NoMatchFor(query)
      }
    }
  }

  fun resultState(): SearchState? {
    return searchStateLiveData.value
  }
}
