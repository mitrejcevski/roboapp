package nl.jovmit.roboapp.search

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InMemorySearchServiceShould {

  @Test
  fun returnEmptyResult() {
    val searchService = InMemorySearchService()

    val result = searchService.search("item")

    assertEquals(emptyList<String>(), result)
  }
}
