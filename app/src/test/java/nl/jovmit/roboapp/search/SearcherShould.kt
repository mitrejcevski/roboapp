package nl.jovmit.roboapp.search

import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nl.jovmit.roboapp.InstantTaskExecutorExtension
import nl.jovmit.roboapp.search.data.SearchState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class, InstantTaskExecutorExtension::class)
class SearcherShould {

  @RelaxedMockK
  private lateinit var repository: SearchRepository

  @RelaxedMockK
  private lateinit var validator: Validator

  private lateinit var searcher: Searcher

  private val query = "::irrelevant::"
  private val valid = true
  private val invalid = false

  @BeforeEach
  fun setUp() {
    searcher = Searcher(validator, repository)
  }

  @Test
  fun validateTheQuery() {
    searcher.search(query)

    verify { validator.validate(query) }
  }

  @Test
  fun searchesTheRepositoryForAValidQuery() {
    every { validator.validate(query) }.answers { valid }

    searcher.search(query)

    verify { repository.search(query) }
  }

  @Test
  fun doesNotSearchTheRepositoryForAnInvalidQuery() {
    every { validator.validate(query) }.answers { invalid }

    searcher.search(query)

    verify { repository wasNot Called }
  }

  @Test
  fun informsWhenTheQueryIsNotValid() {
    every { validator.validate(query) }.answers { invalid }

    searcher.search(query)

    assertEquals(SearchState.InvalidQuery, searcher.searchLiveData.value)
  }
}
