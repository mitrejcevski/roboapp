package nl.jovmit.roboapp.search

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import nl.jovmit.roboapp.search.data.SearchState
import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.OutOfInternetException
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SearchRepositoryShould {

  @RelaxedMockK
  private lateinit var searchService: InMemorySearchService

  private lateinit var searchRepository: SearchRepository

  @BeforeEach
  fun setUp() {
    searchRepository = SearchRepository(searchService)
  }

  @Test
  fun returnMatchingResults() {
    val query = "::irrelevant::"
    val matches = listOf("one", "two")
    every { searchService.search(query) }.answers { matches }

    val result = searchRepository.search(query)

    assertEquals(SearchState.MatchingResults(matches), result)
  }

  @Test
  fun returnSearchingError() {
    val query = "::irrelevant::"
    every { searchService.search(query) }.throws(BadSearchException())

    val result = searchRepository.search(query)

    assertEquals(SearchState.SearchError, result)
  }

  @Test
  fun returnOfflineError() {
    val query = "::irrelevant::"
    every { searchService.search(query) }.throws(OutOfInternetException())

    val result = searchRepository.search(query)

    assertEquals(SearchState.Offline, result)
  }
}
