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

    // region Text Search
    private fun searchByIndexOf(text: String, topic: String): Int {
        var count = 0
        var index = 0

        while (true) {
            index = text.indexOf(topic, index)
            index += if (index != -1) {
                count ++
                topic.length
            } else return count
        }
    }

    private fun searchByWindowed(text: String, topic: String): Int {
        return text.windowed(topic.length) { if (it == topic) 1 else 0 }.sum()
    }

    private fun searchByRegex(text: String, topic: String): Int {
        return Regex(topic).findAll(text).count()
    }

    fun occurrencesForSelection(selection: Int, text: String, topic: String) = when (selection) {
        0 -> searchByIndexOf(text, topic)
        1 -> searchByWindowed(text, topic)
        2 -> searchByRegex(text, topic)
        else -> 0
    }
    // endregion

}