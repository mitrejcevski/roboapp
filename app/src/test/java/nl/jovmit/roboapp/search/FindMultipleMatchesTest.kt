package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class FindMultipleMatchesTest {

  @Test
  fun multipleMatchesFound() {
    val validator = QueryValidator(2)
    val repository = Repository(
      InMemorySearchService(listOf("item one", "else", "one", "one value", "other"))
    )
    val searcher = Searcher(validator, repository)

    searcher.search("one")

    val matches = listOf("item one", "one", "one value")
    assertEquals(SearchState.Matches(matches), searcher.searchStateLiveData.value)
  }
}