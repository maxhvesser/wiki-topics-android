package no.mhl.wikitopics.util

/**
 * Measure the execution time of a given [function], give back the measurement through a provided
 * [callback] and return the result of [function]
 *
 * Note: Whilst measuring execution times this way is purely arbitrary and will provide no
 * real-world comparable results (due to many mitigating factors), the idea here is simply to give
 * some 'metrics' and a bit of fun to what could be a very static app
 *
 * @param callback A function that will consume the execution time
 * @param function The function that is to be measured
 * @return The result of what [function] returns
 */
inline fun <T> measureTimeMillis(callback: (Long) -> Unit, function: () -> T): T {
    val start = System.currentTimeMillis()
    val result = function()
    callback(System.currentTimeMillis() - start)
    return result
}