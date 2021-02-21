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
    /**
     * Iterates over the input [text] starting at a given index until a match is found, loops and
     * repeats, in this instance a separate counter that we create is present. Potentially easier
     * to immediately see what is happening 'under the hood'.
     */
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

    /**
     * Slides one by one over the input for the given length, in this instance producing a 1 if
     * the window matches our [topic] else 0. It will slide over the entire input before
     * eventually summing the resulting values.
     */
    private fun searchByWindowed(text: String, topic: String): Int {
        return text.windowed(topic.length) { if (it == topic) 1 else 0 }.sum()
    }

    /**
     * Matches based on a given pattern, simply in this instance we can just use the topic input.
     * findAll will produce a sequence to iterate through, checking matches.
     */
    private fun searchByRegex(text: String, topic: String): Int {
        return Regex(topic).findAll(text).count()
    }

    /**
     * Three different search methods, but all fairly similar. This is just to give some option
     * and show there are a few ways to solve this. My personal approach would be to use the Regex
     * approach which I feel provides the cleanest and most understandable way to pattern match.
     *
     * As previously mentioned, there is not much between the solutions and each provide an O(n)
     * time complexity.
     */
    fun occurrencesForSelection(selection: Int, text: String, topic: String) = when (selection) {
        0 -> searchByIndexOf(text, topic)
        1 -> searchByWindowed(text, topic)
        2 -> searchByRegex(text, topic)
        else -> 0
    }
    // endregion

}