package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState

class Repository(
  private val availableValues: List<String>
) {

  fun performSearch(query: String): SearchState {
    val matches = availableValues.filter { it.contains(query, true) }
    return if (matches.count() > 1) {
      SearchState.Matches(matches)
    } else if (matches.count() == 1) {
      SearchState.Match(matches.first())
    } else {
      SearchState.NoMatchFor(query)
    }
  }
}