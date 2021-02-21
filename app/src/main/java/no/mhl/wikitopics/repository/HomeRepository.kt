package no.mhl.wikitopics.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import no.mhl.wikitopics.api.WikiService
import no.mhl.wikitopics.api.common.Resource
import no.mhl.wikitopics.api.common.Status
import no.mhl.wikitopics.model.WikiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository
@Inject
constructor(
    private val wikiService: WikiService
) {

    /**
     * Get raw response from the remote source, format as needed as per [Status]
     * @param topic The topic for the desired page
     * @return Response formatted and wrapped as [LiveData]
     */
    fun getWikiPageForTopicFromApi(topic: String): LiveData<Resource<WikiResponse>> {
        val data = MutableLiveData<Resource<WikiResponse>>()

        wikiService.getWikiPageForTopic(page = topic).enqueue(object: Callback<WikiResponse> {
            override fun onFailure(call: Call<WikiResponse>, t: Throwable) {
                data.value = Resource.networkError(t)
            }

            override fun onResponse(call: Call<WikiResponse>, response: Response<WikiResponse>) {
                if (response.body()?.error != null) {
                    data.value = Resource.apiError(response.body())
                } else {
                    data.value = Resource.success(response.body())
                }
            }
        })

        return data
    }

}