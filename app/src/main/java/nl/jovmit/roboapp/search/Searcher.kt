package nl.jovmit.roboapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: QueryValidator,
  private val repository: Repository
) {

  private val _searchStateLiveData =
    MutableLiveData<SearchState>()

  val searchStateLiveData: LiveData<SearchState> =
    _searchStateLiveData

  fun search(query: String) {
    if (validator.validate(query)) {
      val result = repository.performSearch(query)
      _searchStateLiveData.value = result
    } else {
      _searchStateLiveData.value = SearchState.BadQuery
    }
  }
}
