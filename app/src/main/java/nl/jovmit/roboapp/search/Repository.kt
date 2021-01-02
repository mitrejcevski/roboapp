package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException

class Repository(
  private val searchService: SearchService
) {

  fun performSearch(query: String): SearchState {
    return try {
      findMatches(query)
    } catch (badSearchException: BadSearchException) {
      SearchState.BadSearch
    }
  }

  private fun findMatches(query: String): SearchState {
    val matches = searchService.findMatches(query)
    return if (matches.isNotEmpty()) {
      SearchState.Matches(matches)
    } else {
      SearchState.NoMatchFor(query)
    }
  }
}