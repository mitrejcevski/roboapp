package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class FindNoMatchesTest {

  private lateinit var searcher: Searcher

  @BeforeEach
  fun setUp() {
    val minQueryLength = 3
    val validator = QueryValidator(minQueryLength)
    val availableValues = listOf("Item 1", "Another Value")
    val repository = Repository(InMemorySearchService(availableValues))
    searcher = Searcher(validator, repository)
  }

  @Test
  fun noMatchFound() {
    val query = "coffee"

    searcher.search(query)

    assertEquals(SearchState.NoMatchFor(query), searcher.searchStateLiveData.value)
  }

  @Test
  fun emptyQuery() {
    searcher.search("")

    assertEquals(SearchState.BadQuery, searcher.searchStateLiveData.value)
  }

  @Test
  fun shortQuery() {
    searcher.search("abc")

    assertEquals(SearchState.BadQuery, searcher.searchStateLiveData.value)
  }

  @Test
  fun anotherShortQuery() {
    searcher.search("   ab")

    assertEquals(SearchState.BadQuery, searcher.searchStateLiveData.value)
  }
}