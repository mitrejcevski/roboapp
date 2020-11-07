package nl.jovmit.roboapp.search

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SearcherShould {

  @RelaxedMockK
  private lateinit var validator: Validator

  private lateinit var searcher: Searcher

  @BeforeEach
  fun setUp() {
    searcher = Searcher(validator)
  }

  @Test
  fun validateTheQuery() {
    val query = "::irrelevant::"

    searcher.search(query)

    verify { validator.validate(query) }
  }
}
