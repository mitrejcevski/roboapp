package nl.jovmit.roboapp.search

import org.junit.jupiter.api.Assertions.*
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

  private fun searchServiceWithout(
    availableValues: List<String>
  ): SearchService {
    val reversedValues = availableValues.map { it.reversed() }
    return InMemorySearchService(reversedValues)
  }
}