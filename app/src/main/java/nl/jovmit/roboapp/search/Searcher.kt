package nl.jovmit.roboapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: QueryValidator,
  private val repository: Repository,
) : ViewModel() {

  private val _searchStateLiveData =
    MutableLiveData<SearchState>()

  val searchStateLiveData: LiveData<SearchState> =
    _searchStateLiveData

  fun search(query: String) {
    if (validator.validate(query)) {
      performSearch(query)
    } else {
      _searchStateLiveData.value = SearchState.BadQuery
    }
  }

  private fun performSearch(query: String) {
    viewModelScope.launch {
      val result = repository.performSearch(query)
      _searchStateLiveData.value = result
    }
  }
}
