// Please, delete it after adding your implementation
@file:Suppress("UnusedPrivateMember")

package jub.task1.game

import org.jsoup.nodes.Document

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun getHtmlDocument(url: String): Document? {
    // You can use Jsoup.connect to get a document
    // See - https://jsoup.org/cookbook/input/load-document-from-url
    TODO("Not implemented yet")
}

fun extractReferences(html: Document?): List<String> {
    // You can use html.select to find the references
    // See the link to find usage examples: https://www.tabnine.com/code/java/methods/org.jsoup.nodes.Element/select
    // Don't forget to create the full link, e.g. /wiki/JetBrains --> https://en.wikipedia.org/wiki/JetBrains
    TODO("Not implemented yet")
}
