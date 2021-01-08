package nl.jovmit.roboapp.search

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InMemorySearchServiceTest {

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

  private fun searchServiceWithout(
    availableValues: List<String>,
  ): SearchService {
    val reversedValues = availableValues.map { it.reversed() }
    return InMemorySearchService(reversedValues)
  }

  private fun searchServiceWith(
    availableValues: List<String>,
  ): SearchService {
    return InMemorySearchService(availableValues)
  }
}