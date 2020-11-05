package nl.jovmit.roboapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.jovmit.roboapp.search.data.SearchState

class Searcher {
  private val _liveData = MutableLiveData<SearchState>()
  val searchLiveData: LiveData<SearchState> = _liveData

  fun search(query: String) {
    TODO("not implemented")
  }
}
