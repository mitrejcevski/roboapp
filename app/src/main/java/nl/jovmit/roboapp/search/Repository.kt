package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState

class Repository {
  fun performSearch(query: String): SearchState {
    val availableValues = listOf("Item 1", "Another Value")
    return if (availableValues.any { it.contains(query, true) }) {
      val match = availableValues.first { it.contains(query, true) }
      SearchState.Match(match)
    } else {
      SearchState.NoMatchFor(query)
    }
  }
}