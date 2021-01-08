package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.ConnectionUnavailableException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class SearchServiceContract {

  @Test
  fun returnEmptyResult() {
    val searchService = searchServiceWithout(
      listOf("Item 1", "item 2")
    )

    val result = searchService.findMatches("item")

    assertEquals(emptyList<String>(), result)
  }

  @Test
  fun returnMatches() {
    val searchService = searchServiceWith(
      listOf("one", "item 1", "two", "Item 2", "else", "ITEM 3")
    )

    val result = searchService.findMatches("item")

    assertEquals(listOf("item 1", "Item 2", "ITEM 3"), result)
  }

  @Test
  fun throwsABadSearchException() {
    val searchService = unavailableSearchService()

    assertThrows<BadSearchException> {
      searchService.findMatches("")
    }
  }

  @Test
  fun throwsAnOfflineException() {
    val searchService = offlineSearchService()

    assertThrows<ConnectionUnavailableException> {
      searchService.findMatches("whatever")
    }
  }

  protected abstract fun offlineSearchService(): SearchService

  protected abstract fun unavailableSearchService(): SearchService

  protected abstract fun searchServiceWithout(
    availableValues: List<String>,
  ): SearchService

  protected abstract fun searchServiceWith(
    availableValues: List<String>,
  ): SearchService
}