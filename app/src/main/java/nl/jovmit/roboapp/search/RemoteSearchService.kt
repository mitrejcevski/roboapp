package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.ConnectionUnavailableException
import retrofit2.HttpException
import java.io.IOException

class RemoteSearchService(
  private val searchApi: SearchApi,
) : SearchService {

  override fun findMatches(
    query: String,
  ): List<String> {
    try {
      return searchApi.findMatches(query)
    } catch (httpException: HttpException) {
      throw BadSearchException()
    } catch (offlineException: IOException) {
      throw ConnectionUnavailableException()
    }
  }
}
