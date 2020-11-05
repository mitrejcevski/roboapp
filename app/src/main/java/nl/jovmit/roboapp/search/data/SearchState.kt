package nl.jovmit.roboapp.search.data

sealed class SearchState {

  data class MatchingResults(
    val matches: List<String>
  ) : SearchState()

  object ShowLoading : SearchState()

  object HideLoading : SearchState()
}
