package no.mhl.wikitopics.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import no.mhl.wikitopics.api.common.Resource
import no.mhl.wikitopics.model.WikiResponse
import no.mhl.wikitopics.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    // region Remote Interaction
    fun pageForTopic(topic: String): LiveData<Resource<WikiResponse>> = homeRepository
            .getWikiPageForTopicFromApi(topic)
    // endregion

}