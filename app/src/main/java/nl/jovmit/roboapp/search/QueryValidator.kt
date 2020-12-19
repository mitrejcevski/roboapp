package nl.jovmit.roboapp.search

class QueryValidator(
  private val minQueryLength: Int
) {

  fun validate(query: String): Boolean {
    return query.trim().length > minQueryLength
  }
}