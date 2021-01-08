package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException

class InMemorySearchService(
  private val availableValues: List<String>
) : SearchService {

  override fun findMatches(query: String): List<String> {
    if(query.isBlank()) throw BadSearchException()
    return availableValues.filter { it.contains(query, true) }
  }
}