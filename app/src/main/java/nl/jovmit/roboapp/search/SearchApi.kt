package nl.jovmit.roboapp.search

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

  @GET("/search")
  fun findMatches(
    @Query("keyword") query: String,
  ): List<String>
}