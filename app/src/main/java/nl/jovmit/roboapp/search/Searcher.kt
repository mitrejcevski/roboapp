package nl.jovmit.roboapp.search

import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: QueryValidator
) {

  private val searchStateLiveData =
    MutableLiveData<SearchState>()

  fun search(query: String) {
    if (validator.validate(query)) {
      if (query == "item") {
        searchStateLiveData.value = SearchState.Match("Item 1")
      } else if (query == "another") {
        searchStateLiveData.value = SearchState.Match("Another Value")
      } else {
        searchStateLiveData.value = SearchState.NoMatchFor(query)
      }
    } else {
      searchStateLiveData.value = SearchState.BadQuery
    }
  }

  fun resultState(): SearchState? {
    return searchStateLiveData.value
  }
}
