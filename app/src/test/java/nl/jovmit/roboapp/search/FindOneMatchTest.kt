package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class FindOneMatchTest {

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
  fun matchFound() {
    searcher.search("item")

    assertEquals(SearchState.Matches(listOf("Item 1")), searcher.searchStateLiveData.value)
  }

  @Test
  fun anotherMatchFound() {
    searcher.search("another")

    assertEquals(SearchState.Matches(listOf("Another Value")), searcher.searchStateLiveData.value)
  }
}