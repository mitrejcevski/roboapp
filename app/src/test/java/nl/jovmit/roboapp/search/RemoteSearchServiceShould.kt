package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.api.SearchApi
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RemoteSearchServiceShould : SearchServiceContract() {

  override fun searchServiceWithout(
    allItems: List<String>
  ): SearchService {
    val reversedItems = allItems.map { it.reversed() }
    return RemoteSearchService(StubSearchApi(reversedItems))
  }

  override fun searchServiceWith(
    allItems: List<String>
  ): SearchService {
    return RemoteSearchService(StubSearchApi(allItems))
  }

  override fun offlineSearchService(): SearchService {
    return RemoteSearchService(OfflineApi())
  }

  class StubSearchApi(
    private val allItems: List<String>
  ) : SearchApi {

    override fun search(keyword: String): List<String> {
      validate(keyword)
      return allItems.filter { it.contains(keyword, true) }
    }

    private fun validate(keyword: String) {
      if (keyword.isBlank()) {
        val body = ResponseBody.create(null, "empty keyword")
        val response = Response.error<String>(403, body)
        throw HttpException(response)
      }
    }
  }

  class OfflineApi : SearchApi {
    override fun search(keyword: String): List<String> {
      throw IOException()
    }
  }
}