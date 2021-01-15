package nl.jovmit.roboapp.search

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RemoteSearchServiceTest : SearchServiceContract() {

  override fun offlineSearchService(): SearchService {
    val api = OfflineSearchApi()
    return RemoteSearchService(api)
  }

  override fun unavailableSearchService(): SearchService {
    return RemoteSearchService(UnavailableSearchApi())
  }

  override fun searchServiceWithout(
    availableValues: List<String>,
  ): SearchService {
    val reversedValues = availableValues.map { it.reversed() }
    return RemoteSearchService(AvailableSearchService(reversedValues))
  }

  override fun searchServiceWith(
    availableValues: List<String>,
  ): SearchService {
    return RemoteSearchService(AvailableSearchService(availableValues))
  }

  class UnavailableSearchApi : SearchApi {

    override fun findMatches(query: String): List<String> {
      val errorBody = ResponseBody.create(null, "Bad query")
      val badResponse = Response.error<String>(406, errorBody)
      throw HttpException(badResponse)
    }
  }

  class AvailableSearchService(
    private val availableValues: List<String>,
  ) : SearchApi {

    override fun findMatches(query: String): List<String> {
      return availableValues.filter { it.contains(query, true) }
    }
  }

  class OfflineSearchApi : SearchApi {

    override fun findMatches(query: String): List<String> {
      throw IOException()
    }
  }
}