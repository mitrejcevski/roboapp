package nl.jovmit.roboapp.search

import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: QueryValidator,
  private val repository: Repository
) {

  private val searchStateLiveData =
    MutableLiveData<SearchState>()

  fun search(query: String) {
    if (validator.validate(query)) {
      val result = repository.performSearch(query)
      searchStateLiveData.value = result
    } else {
      searchStateLiveData.value = SearchState.BadQuery
    }
  }

  fun resultState(): SearchState? {
    return searchStateLiveData.value
  }
}
