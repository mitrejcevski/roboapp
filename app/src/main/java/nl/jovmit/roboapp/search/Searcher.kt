package nl.jovmit.roboapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: Validator,
  private val repository: SearchRepository
) {

  private val _liveData = MutableLiveData<SearchState>()
  val searchLiveData: LiveData<SearchState> = _liveData

  fun search(query: String) {
    if (validator.validate(query)) {
      repository.search(query)
    } else {
      _liveData.value = SearchState.InvalidQuery
    }
  }
}
