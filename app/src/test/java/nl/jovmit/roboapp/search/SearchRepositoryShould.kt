package nl.jovmit.roboapp.search

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.Assert.*
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
}
