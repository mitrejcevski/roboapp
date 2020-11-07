package nl.jovmit.roboapp.search

import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SearcherShould {

  @RelaxedMockK
  private lateinit var repository: SearchRepository

  @RelaxedMockK
  private lateinit var validator: Validator

  private lateinit var searcher: Searcher

  @BeforeEach
  fun setUp() {
    searcher = Searcher(validator, repository)
  }

  @Test
  fun validateTheQuery() {
    val query = "::irrelevant::"

    searcher.search(query)

    verify { validator.validate(query) }
  }

  @Test
  fun searchesTheRepositoryForAValidQuery() {
    val query = "::irrelevant::"
    val valid = true
    every { validator.validate(query) }.answers { valid }

    searcher.search(query)

    verify { repository.search(query) }
  }

  @Test
  fun doesNotSearchTheRepositoryForAnInvalidQuery() {
    val query = "::irrelevant::"
    val invalid = false
    every { validator.validate(query) }.answers { invalid }

    searcher.search(query)

    verify { repository wasNot Called }
  }
}
