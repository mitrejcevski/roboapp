package nl.jovmit.roboapp.search

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RemoteSearchServiceTest : SearchServiceContract() {

  override fun offlineSearchService(): SearchService {
    return RemoteSearchService(OfflineSearchApi())
  }

  override fun unavailableSearchService(): SearchService {
    return RemoteSearchService(UnavailableSearchApi())
  }

  override fun searchServiceWithout(
    availableValues: List<String>,
  ): SearchService {
    val reversedValues = availableValues.map { it.reversed() }
    return RemoteSearchService(AvailableSearchApi(reversedValues))
  }

  override fun searchServiceWith(
    availableValues: List<String>,
  ): SearchService {
    return RemoteSearchService(AvailableSearchApi(availableValues))
  }

  class AvailableSearchApi(
    private val availableValues: List<String>,
  ) : SearchApi {

    override suspend fun findMatches(query: String): List<String> {
      return availableValues.filter { it.contains(query, true) }
    }
  }

  class UnavailableSearchApi : SearchApi {

    override suspend fun findMatches(query: String): List<String> {
      val errorBody = ResponseBody.create(null, "Bad query")
      val badResponse = Response.error<String>(406, errorBody)
      throw HttpException(badResponse)
    }
  }

  class OfflineSearchApi : SearchApi {

    override suspend fun findMatches(query: String): List<String> {
      throw IOException()
    }
  }
}