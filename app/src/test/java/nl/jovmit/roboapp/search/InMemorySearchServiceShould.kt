package nl.jovmit.roboapp.search

class InMemorySearchServiceShould : SearchServiceContract() {

  override fun searchServiceWithout(
    allItems: List<String>
  ): SearchService {
    val reversedItems = allItems.map { it.reversed() }
    return InMemorySearchService(reversedItems)
  }

  override fun searchServiceWith(
    allItems: List<String>
  ): SearchService {
    return InMemorySearchService(allItems)
  }

  override fun offlineSearchService(): SearchService {
    return InMemorySearchService(null)
  }
}
