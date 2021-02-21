package no.mhl.wikitopics.model

import com.google.gson.annotations.SerializedName

/**
 * Error response from the Wiki API
 * @property code Error code used by said APi
 * @property info: Information regarding the error
 * @property raw: The raw returned page as as HTML string
 */
data class WikiError(
    val code: String,
    val info: String,
    @SerializedName("*")
    val raw: String
)