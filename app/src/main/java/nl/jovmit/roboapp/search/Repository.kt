package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState

class Repository(
  private val searchService: SearchService
) {

  fun performSearch(query: String): SearchState {
    val matches = searchService.findMatches(query)
    return if (matches.isNotEmpty()) {
      SearchState.Matches(matches)
    } else {
      SearchState.NoMatchFor(query)
    }
  }
}