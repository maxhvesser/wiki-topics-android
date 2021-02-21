package no.mhl.wikitopics.model

import com.google.gson.annotations.SerializedName

/**
 * Page object returned from the Wiki API
 * @property title The title of this page, otherwise known as the topic
 * @property pageId Unique ID for the page
 * @property text The text for the page wrapped in a [WikiText] object
 */
data class WikiPage(
    val title: String,
    @SerializedName("pageid")
    val pageId: Long,
    val text: WikiText
)