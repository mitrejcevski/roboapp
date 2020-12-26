package nl.jovmit.roboapp.search

interface SearchService {
  fun findMatches(query: String): List<String>
}