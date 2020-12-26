package nl.jovmit.roboapp.search

class InMemorySearchService(
  private val availableValues: List<String>
) : SearchService {

  override fun findMatches(query: String): List<String> {
    return availableValues.filter { it.contains(query, true) }
  }
}