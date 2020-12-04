package nl.jovmit.roboapp.search

class Searcher {

  private var result: String? = null

  fun search(query: String) {
    result = "Item 1"
  }

  fun getResult(): String? {
    return result
  }
}
