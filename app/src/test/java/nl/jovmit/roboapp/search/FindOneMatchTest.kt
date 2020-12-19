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
    searcher = Searcher()
  }

  @Test
  fun matchFound() {
    searcher.search("item")

    assertEquals(SearchState.Match("Item 1"), searcher.resultState())
  }

  @Test
  fun anotherMatchFound() {
    searcher.search("another")

    assertEquals(SearchState.Match("Another Value"), searcher.resultState())
  }

  @Test
  fun noMatchFound() {
    val query = "coffee"

    searcher.search(query)

    assertEquals(SearchState.NoMatchFor(query), searcher.resultState())
  }

  @Test
  fun emptyQuery() {
    searcher.search("")

    assertEquals(SearchState.BadQuery, searcher.resultState())
  }

  @Test
  fun shortQuery() {
    searcher.search("abc")

    assertEquals(SearchState.BadQuery, searcher.resultState())
  }

  @Test
  fun anotherShortQuery() {
    searcher.search("   ab")

    assertEquals(SearchState.BadQuery, searcher.resultState())
  }
}