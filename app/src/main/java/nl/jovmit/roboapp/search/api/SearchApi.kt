package nl.jovmit.roboapp.search.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

  @GET("/lookup")
  fun search(
    @Query("keyword") keyword: String
  ): List<String>
}
