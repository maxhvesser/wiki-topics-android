package no.mhl.wikitopics.api.common

import no.mhl.wikitopics.api.common.Status.Success
import no.mhl.wikitopics.api.common.Status.NetworkError
import no.mhl.wikitopics.api.common.Status.ApiError

/**
 * A response wrapper class around objects returned from remote sources
 * @property status  The [Status] of the response
 * @property data The returned data of generic type
 * @property throwable If applicable the [Throwable] object from an error
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val throwable: Throwable?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Success, data, null)
        fun networkError(error: Throwable?) = Resource(NetworkError, null, error)
        fun <T> apiError(data: T?): Resource<T> = Resource(ApiError, data, null)
    }

}