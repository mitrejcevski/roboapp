package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class FindOneMatchTest {

  @Test
  fun matchFound() {
    val searcher = Searcher()

    searcher.search("item")

    assertEquals(SearchState.Match("Item 1"), searcher.resultState())
  }

  @Test
  fun anotherMatchFound() {
    val searcher = Searcher()

    searcher.search("another")

    assertEquals(SearchState.Match("Another Item"), searcher.resultState())
  }

  @Test
  fun noMatchFound() {
    val query = "coffee"
    val searcher = Searcher()

    searcher.search(query)

    assertEquals(SearchState.NoMatchFor(query), searcher.resultState())
  }

  @Test
  fun emptyQuery() {
    val searcher = Searcher()

    searcher.search("")

    assertEquals(SearchState.BadQuery, searcher.resultState())
  }

  @Test
  fun shortQuery() {
    val searcher = Searcher()

    searcher.search("abc")

    assertEquals(SearchState.BadQuery, searcher.resultState())
  }
}