package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.OutOfInternetException

class SearchRepository(
  private val searchService: InMemorySearchService
) {

  fun search(query: String): SearchState {
    return try {
      val matches = searchService.search(query)
      SearchState.MatchingResults(matches)
    } catch (badSearchException: BadSearchException) {
      SearchState.SearchError
    } catch (outOfInternetException: OutOfInternetException) {
      SearchState.Offline
    }
  }
}
