package nl.jovmit.roboapp.search.data

sealed class SearchState {

  object BadQuery : SearchState()

  object BadSearch : SearchState()

  data class Matches(val matches: List<String>) : SearchState()

  data class NoMatchFor(val query: String) : SearchState()
}
