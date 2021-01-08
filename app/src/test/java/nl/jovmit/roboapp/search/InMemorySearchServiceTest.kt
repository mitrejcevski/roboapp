package nl.jovmit.roboapp.search

class InMemorySearchServiceTest : SearchServiceContract() {

  override fun offlineSearchService(): SearchService {
    return InMemorySearchService(null)
  }

  override fun unavailableSearchService(): SearchService {
    return InMemorySearchService(emptyList())
  }

  override fun searchServiceWithout(
    availableValues: List<String>,
  ): SearchService {
    val reversedValues = availableValues.map { it.reversed() }
    return InMemorySearchService(reversedValues)
  }

  override fun searchServiceWith(
    availableValues: List<String>,
  ): SearchService {
    return InMemorySearchService(availableValues)
  }
}