// Please, delete it after adding your implementation
@file:Suppress("UnusedPrivateMember")

package jub.task1.game

// Stores the number of steps and a list of all links to the destination page
// The path must include the final page
data class SearchPath(
    val steps: Int,
    val path: List<String>,
)

class PageSearch(
    private val finalPage: String = KOTLIN_PAGE,
) {
    fun search(startPage: String, searchDepth: Int, threadsCount: Int): SearchPath = TODO("Not implemented yet")

    companion object {
        const val KOTLIN_PAGE = "https://en.wikipedia.org/wiki/Kotlin_(programming_language)"
        const val NOT_FOUND = -1
    }
}
