package nl.jovmit.roboapp.search

class QueryValidator {

  fun validate(query: String): Boolean {
    return query.trim().length > 3
  }
}