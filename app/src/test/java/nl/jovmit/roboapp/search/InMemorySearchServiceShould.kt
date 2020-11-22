package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException
import org.junit.Assert.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InMemorySearchServiceShould {

  @Test
  fun returnEmptyResult() {
    val searchService = InMemorySearchService()

    val result = searchService.search("item")

    assertEquals(emptyList<String>(), result)
  }

  @Test
  fun returnValuesContainingTheQuery() {
    val searchService = InMemorySearchService(
      listOf("one", "item 1", "two", "Item 2", "ITEM 3", "other")
    )

    val result = searchService.search("item")

    assertEquals(listOf("item 1", "Item 2", "ITEM 3"), result)
  }

  @Test
  fun throwsABadSearchException() {
    val searchService = InMemorySearchService()

    assertThrows<BadSearchException> {
      searchService.search("")
    }
  }
}
