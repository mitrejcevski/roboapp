package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.OutOfInternetException
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class SearchServiceContract {

  @Test
  fun returnEmptyResult() {
    val searchService = searchServiceWithout(
      listOf("item 1", "item 2")
    )

    val result = searchService.search("item")

    assertEquals(emptyList<String>(), result)
  }

  @Test
  fun returnValuesContainingTheQuery() {
    val searchService = searchServiceWith(
      listOf("one", "item 1", "two", "Item 2", "ITEM 3", "other")
    )

    val result = searchService.search("item")

    assertEquals(listOf("item 1", "Item 2", "ITEM 3"), result)
  }

  @Test
  fun throwsABadSearchException() {
    val searchService = searchServiceWithout()

    assertThrows<BadSearchException> {
      searchService.search("")
    }
  }

  @Test
  fun throwAnOutOfInternetException() {
    val searchService = offlineSearchService()

    assertThrows<OutOfInternetException> {
      searchService.search("query")
    }
  }

  protected abstract fun searchServiceWithout(
    allItems: List<String> = emptyList()
  ): SearchService

  protected abstract fun searchServiceWith(
    allItems: List<String>
  ): SearchService

  protected abstract fun offlineSearchService(): SearchService
}
