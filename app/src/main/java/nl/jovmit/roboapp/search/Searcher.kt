package nl.jovmit.roboapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.jovmit.roboapp.search.data.SearchState

class Searcher(
  private val validator: Validator,
  private val repository: SearchRepository
) : ViewModel() {

  private val _liveData = MutableLiveData<SearchState>()
  val searchLiveData: LiveData<SearchState> = _liveData

  fun search(query: String) {
    if (validator.validate(query)) {
      proceedSearch(query)
    } else {
      _liveData.value = SearchState.InvalidQuery
    }
  }

  private fun proceedSearch(query: String) {
    _liveData.value = SearchState.ShowLoading
    viewModelScope.launch {
      val results = withContext(Dispatchers.IO) {
        repository.search(query)
      }
      _liveData.value = results
      _liveData.value = SearchState.HideLoading
    }
  }
}
