package nl.jovmit.roboapp.search

interface SearchService {
  suspend fun findMatches(query: String): List<String>
}