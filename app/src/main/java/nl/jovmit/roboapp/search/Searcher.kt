package nl.jovmit.roboapp.search

class Searcher {

  private var result: String? = null

  fun search(query: String) {
    if (query == "item") {
      result = "Item 1"
    } else if (query == "another") {
      result = "Another Item"
    }
  }

  fun getResult(): String? {
    return result
  }
}
