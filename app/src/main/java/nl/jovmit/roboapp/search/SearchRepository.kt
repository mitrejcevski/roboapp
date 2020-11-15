package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState

class SearchRepository(
  private val searchService: InMemorySearchService
) {

  fun search(query: String): SearchState {
    val matches = searchService.search(query)
    return SearchState.MatchingResults(matches)
  }
}
