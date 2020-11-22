package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException

class InMemorySearchService(
  private val allItems: List<String> = emptyList()
) : SearchService {

  override fun search(
    query: String
  ): List<String> {
    if(query.isBlank()) throw BadSearchException()
    return allItems.filter { it.contains(query, true) }
  }
}
