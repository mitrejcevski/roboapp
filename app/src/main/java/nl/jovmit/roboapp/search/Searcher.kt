package nl.jovmit.roboapp.search

class Searcher {

  private var result: String? = null

  fun search(query: String) {
    if(query.isBlank()) {
      result = "Error: bad query"
    } else {
      if (query == "item") {
        result = "Item 1"
      } else if (query == "another") {
        result = "Another Item"
      } else {
        result = "No match found for $query"
      }
    }
  }

  fun getResult(): String? {
    return result
  }
}
