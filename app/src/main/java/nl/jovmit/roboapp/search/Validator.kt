package nl.jovmit.roboapp.search

class Validator {

  fun validate(query: String): Boolean {
    return query.trim().isNotBlank()
        && query.isLongEnough()
  }

  private fun String.isLongEnough() =
    this.length > 3
}
