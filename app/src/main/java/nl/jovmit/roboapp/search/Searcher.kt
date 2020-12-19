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
      val availableValues = listOf("Item 1", "Another Value")
      val hasValue = availableValues.any { it.contains(query, true) }
      if (hasValue) {
        val match = availableValues.first { it.contains(query, true) }
        searchStateLiveData.value = SearchState.Match(match)
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
