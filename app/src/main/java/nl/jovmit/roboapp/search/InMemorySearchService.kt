package nl.jovmit.roboapp.search

class InMemorySearchService(
  private val allItems: List<String> = emptyList()
) : SearchService {

  override fun search(
    query: String
  ): List<String> {
    return allItems.filter { it.contains(query, true) }
  }
}
