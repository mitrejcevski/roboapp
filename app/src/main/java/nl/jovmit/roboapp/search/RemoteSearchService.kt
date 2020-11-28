package nl.jovmit.roboapp.search

import nl.jovmit.roboapp.search.api.SearchApi
import nl.jovmit.roboapp.search.exception.BadSearchException
import nl.jovmit.roboapp.search.exception.OutOfInternetException
import retrofit2.HttpException
import java.io.IOException

class RemoteSearchService(
  private val api: SearchApi
) : SearchService {

  override fun search(query: String): List<String> {
    try {
      return api.search(query)
    } catch (httpException: HttpException) {
      throw BadSearchException()
    } catch (ioException: IOException) {
      throw OutOfInternetException()
    }
  }
}
