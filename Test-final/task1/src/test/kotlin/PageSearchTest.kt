import jub.task1.game.PageSearch
import jub.task1.game.PageSearch.Companion.KOTLIN_PAGE
import jub.task1.game.PageSearch.Companion.NOT_FOUND
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PageSearchTest {
    @ParameterizedTest
    @MethodSource("searchTestData")
    fun searchTestSingleThread(expectedPathSize: Int, startPage: String, expectedPath: List<String>) {
        val searchResult = PageSearch(KOTLIN_PAGE).search(startPage, 2, 1)
        assertEquals(expectedPathSize, searchResult.steps, "From $startPage to $KOTLIN_PAGE $expectedPathSize steps")
        expectedPath.zip(searchResult.path).forEachIndexed { index, (expected, actual) ->
            assertEquals(
                expected,
                actual,
                "The $index-th item in the path from $startPage to $KOTLIN_PAGE is $expected, but was $actual"
            )
        }
    }

    companion object {
        private const val COMPARISON_PAGE = "https://en.wikipedia.org/wiki/Comparison_of_programming_languages"
        private const val JETBRAINS_PAGE = "https://en.wikipedia.org/wiki/JetBrains"
        private const val JVM_PAGE = "https://en.wikipedia.org/wiki/Java_virtual_machine"
        private const val AVL_PAGE = "https://en.wikipedia.org/wiki/AVL_tree"

        @JvmStatic
        fun searchTestData() = listOf(
            Arguments.of(0, KOTLIN_PAGE, listOf(KOTLIN_PAGE)),
            Arguments.of(1, COMPARISON_PAGE, listOf(COMPARISON_PAGE, KOTLIN_PAGE)),
            Arguments.of(1, JETBRAINS_PAGE, listOf(JETBRAINS_PAGE, KOTLIN_PAGE)),
            Arguments.of(1, JVM_PAGE, listOf(JVM_PAGE, KOTLIN_PAGE)),
            Arguments.of(2, AVL_PAGE, listOf(AVL_PAGE, "https://en.wikipedia.org/wiki/Tail_call", KOTLIN_PAGE)),
            Arguments.of(NOT_FOUND, "https://en.wikipedia.org/wiki/Bremen", emptyList<String>()),
        )
    }
}
