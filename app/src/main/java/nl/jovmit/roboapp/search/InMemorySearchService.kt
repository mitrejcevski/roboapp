package nl.jovmit.roboapp.search

class InMemorySearchService : SearchService {

  override fun search(
    query: String
  ): List<String> {
    return emptyList()
  }
}
