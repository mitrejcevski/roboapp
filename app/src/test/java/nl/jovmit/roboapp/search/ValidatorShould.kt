package nl.jovmit.roboapp.search

import org.junit.Assert.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ValidatorShould {

  @ParameterizedTest
  @CsvSource(
    value = [
      "'', false",
      "'  ', false",
      "'abc', false",
      "'abcd', true"
    ]
  )
  fun informWhenTheQueryIsValid(query: String, isValid: Boolean) {
    val validator = Validator()
    assertEquals(validator.validate(query), isValid)
  }
}
