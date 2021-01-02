package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException

class Repository(
  private val searchService: SearchService
) {

  fun performSearch(query: String): SearchState {
    try {
      val matches = searchService.findMatches(query)
      return if (matches.isNotEmpty()) {
        SearchState.Matches(matches)
      } else {
        SearchState.NoMatchFor(query)
      }
    } catch (e: BadSearchException) {
      return SearchState.BadSearch
    }
  }
}