package nl.jovmit.roboapp.search.data

sealed class SearchState {

  object BadQuery : SearchState()

  data class Match(val value: String) : SearchState()

  data class NoMatchFor(val query: String) : SearchState()
}
