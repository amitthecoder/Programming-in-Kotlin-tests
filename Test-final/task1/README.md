### Task #1 (15 points)

You may have heard about the game “Wikipedia race”, where you click on links in Wikipedia articles with the objective of getting from one article to another in as few clicks as possible. You can click on any link on the page. Playing the game is fun – and it’s even more fun when you win! In this task, you need to implement a breadth-first search to find the shortest path between pages.

Your solution should be a console app with the following arguments:
- Search depth – How many transitions from the start page are accepted. Don't forget to check if the depth is > 0 and inform the user if the input is incorrect.
- Number of processors – How many processors to use (the default value is one). Don't forget to check if the number of processors is > 0 and inform the user if the input is incorrect.
- The optional title of the article – If passed, then the solution should start searching from this page, otherwise it should start from a random page. For example, for the title `Comparison of programming languages` the start page will be `https://en.wikipedia.org/wiki/Comparison_of_programming_languages`. You don't need to generate random page names to start, just take a random element from a pre-prepared list with the article names.

Feel free to use [Clikt](https://ajalt.github.io/clikt/) or any other library to handle the command-line arguments.

The output of the app is the sequence of pages in the found path or a message saying that no path was found within the search depth. Consider displaying the progress, so that the user knows that the application has not frozen.

To extract links from a Wiki page, you need to extract all internal (referring to any Wiki page) links that are inside the `a` tag (`html.select("[href^=/wiki/]").map { it.attr("href") }`). To make the search faster, you need to exclude the following links when parsing:
```kotlin
val forbiddenTemplates = listOf(
    "File:",
    "Wikipedia:",
    "Help:",
    "Template:",
    "Category:",
    "Special:",
    "Portal:",
    "User:",
    "MediaWiki:",
    "Draft",
    "TimedText",
    "Module:",
    "Media:",
    "Template_talk:",
    "Talk:"
)
```
If at least one of the elements in this list is in the final list with the references, this link should not be taken into account.

The goal page for this task is the Kotlin page that is stored in the `KOTLIN_PAGE` constant. If the user starts the search from this page, the depth of the final path is 0. If the user starts with a page which refers to the Kotlin page, the depth of the final path is 1, etc. You can find several examples in the tests.

10 points are available for the simple version. If you would like to get all 15 points, you need to use a multi-threaded search (in this case, you just need to find a path as quickly as possible, not necessarily the shortest path).
