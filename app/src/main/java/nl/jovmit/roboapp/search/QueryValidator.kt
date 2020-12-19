package nl.jovmit.roboapp.search

class QueryValidator {
  fun validate(query: String) = query.trim().length > 3
}