package nl.jovmit.roboapp.search

interface SearchService {
  fun search(query: String): List<String>
}
