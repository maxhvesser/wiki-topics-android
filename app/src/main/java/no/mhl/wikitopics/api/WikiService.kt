package no.mhl.wikitopics.api

import no.mhl.wikitopics.api.common.Constants.ENDPOINT_API
import no.mhl.wikitopics.model.WikiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiService {

    /**
     * Remote call to get a Wiki page response object from a given topic
     * @param page The topic for which page we want to be returned
     * @return The response [Any] wrapped in a [Call]
     */
    @GET(ENDPOINT_API)
    fun getWikiPageForTopic(
        @Query("action") action: String = "parse",
        @Query("section") section: Int = 0,
        @Query("prop") prop: String = "text",
        @Query("format") format: String = "json",
        @Query("page") page: String
    ): Call<WikiResponse>

}