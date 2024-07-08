import jub.task1.game.extractReferences
import jub.task1.game.getHtmlDocument
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ParserTests {

    companion object {
        @JvmStatic
        fun htmlPages() = listOf(
            Arguments.of(
                "https://en.wikipedia.org/wiki/Kotlin_(programming_language)aaaaa",
                null,
            ),
            Arguments.of(
                "en.wikipedia.org/wiki/Kotlin_(programming_language)aaaaa",
                null,
            ),
            Arguments.of(
                "https://en.wikipedia.org/wiki/Kotlin_(programming_language)",
                listOf(
                    "a new language for the JVM, which had been under development for a year",
                    "Kotlin 1.5 was released in May 2021",
                    "by default, meaning that creating a derived class is disabled unless the base class is declared with",
                )
            ),
            Arguments.of(
                "en.wikipedia.org/wiki/Kotlin_(programming_language)",
                listOf(
                    "a new language for the JVM, which had been under development for a year",
                    "Kotlin 1.5 was released in May 2021",
                    "by default, meaning that creating a derived class is disabled unless the base class is declared with",
                )
            )
        )

        @JvmStatic
        fun referencesData() = listOf(
            Arguments.of(
                "https://en.wikipedia.org/wiki/Kotlin_(programming_language)aaaaa",
                emptyList<String>(),
                0,
            ),
            Arguments.of(
                "https://en.wikipedia.org/wiki/Kotlin_(programming_language)",
                listOf(
                    "https://en.wikipedia.org/wiki/James_Gosling",
                    "https://en.wikipedia.org/wiki/Java_Community_Process",
                    "https://en.wikipedia.org/wiki/JetBrains",
                ),
                210
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("htmlPages")
    fun getHtmlDocumentTest(url: String, output: List<String>?) {
        val html = getHtmlDocument(url)
        output?.let {
            assertNotNull(
                html,
                "For the valid url: $url the parser must return not null! User can omit https://, but you need to handle this case!"
            )
            output.forEach {
                assertTrue(it in html.toString(), "For the valid url: $url the text $output must be in the output")
            }
        } ?: assertNull(html, "For the invalid url: $url the parser must return null!")
    }

    @ParameterizedTest
    @MethodSource("referencesData")
    fun referencesExtractorTest(url: String, expectedReferences: List<String>, referencesNumber: Int) {
        val html = getHtmlDocument(url)
        val references = extractReferences(html)
        assertTrue(
            references.size == referencesNumber,
            "For thw url: $url you need to extract $referencesNumber references."
        )
        expectedReferences.forEach {
            assertTrue(it in references, "The reference: $it must be found by the $url url.")
        }
    }
}
