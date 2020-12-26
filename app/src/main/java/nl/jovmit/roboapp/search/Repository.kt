package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState

class Repository(
  private val availableValues: List<String>
) {

  fun performSearch(query: String): SearchState {
    val matches = availableValues.filter { it.contains(query, true) }
    return if (matches.isNotEmpty()) {
      SearchState.Matches(matches)
    } else {
      SearchState.NoMatchFor(query)
    }
  }
}