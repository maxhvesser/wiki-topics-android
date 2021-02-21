package no.mhl.wikitopics.model

import com.google.gson.annotations.SerializedName

/**
 * Due to how the response is formatted a nice way to deal with this is created an model such as
 * this. Similarly a [Map] could have been used, but that would require accessing singular items,
 * whereas for this project we assume we are returned a single object.
 * @property raw The raw text returned as a HTML string
 */
data class WikiText(
    @SerializedName("*")
    val raw: String
)
