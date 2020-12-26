package nl.jovmit.roboapp.search

class InMemorySearchService(
  private val availableValues: List<String>
) {

  fun findMatches(query: String): List<String> {
    return availableValues.filter { it.contains(query, true) }
  }
}