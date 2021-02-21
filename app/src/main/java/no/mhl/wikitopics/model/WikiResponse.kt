package no.mhl.wikitopics.model

import com.google.gson.annotations.SerializedName

/**
 * Top level response object for the Wiki API
 * @property page The page object, not present if [error] is present
 * @property error The error object, not present if [page] is present
 */
data class WikiResponse(
    @SerializedName("parse")
    val page: WikiPage,
    val error: WikiError
)
