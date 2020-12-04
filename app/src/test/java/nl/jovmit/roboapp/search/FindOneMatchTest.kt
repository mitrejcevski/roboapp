package nl.jovmit.roboapp.search

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FindOneMatchTest {

  @Test
  fun matchFound() {
    val searcher = Searcher()

    searcher.search("item")

    assertEquals("Item 1", searcher.getResult())
  }
}