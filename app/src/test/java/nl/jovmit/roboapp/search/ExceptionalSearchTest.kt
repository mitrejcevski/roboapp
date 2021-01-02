package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.ConnectionUnavailableException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class ExceptionalSearchTest {

  private val minQueryLength = 3
  private val validator = QueryValidator(minQueryLength)

  @Test
  fun badSearchException() {
    val repository = Repository(UnavailableSearchService())
    val searcher = Searcher(validator, repository)

    searcher.search("irrelevant")

    assertEquals(SearchState.BadSearch, searcher.searchStateLiveData.value)
  }

  @Test
  fun offlineException() {
    val repository = Repository(OfflineSearchService())
    val searcher = Searcher(validator, repository)

    searcher.search("irrelevant")

    assertEquals(SearchState.Offline, searcher.searchStateLiveData.value)
  }

  class OfflineSearchService : SearchService {

    override fun findMatches(query: String): List<String> {
      throw ConnectionUnavailableException()
    }
  }

  class UnavailableSearchService : SearchService {

    override fun findMatches(query: String): List<String> {
      throw BadSearchException()
    }
  }
}