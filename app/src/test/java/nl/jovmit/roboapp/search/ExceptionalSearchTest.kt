package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class ExceptionalSearchTest {

  @Test
  fun badSearchException() {
    val validator = QueryValidator(3)
    val repository = Repository(UnavailableSearchService())
    val searcher = Searcher(validator, repository)

    searcher.search("irrelevant")

    assertEquals(SearchState.BadSearch, searcher.searchStateLiveData.value)
  }

  class UnavailableSearchService : SearchService {

    override fun findMatches(query: String): List<String> {
      throw BadSearchException()
    }
  }
}