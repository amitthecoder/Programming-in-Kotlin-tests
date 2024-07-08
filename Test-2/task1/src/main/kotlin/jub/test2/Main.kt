package jub.test2

import jub.test2.api.Coordinates
import jub.test2.api.WeatherResponse
import org.jetbrains.kotlin.com.google.gson.Gson
import org.jetbrains.kotlin.com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

var TOKEN: String = System.getenv("TOKEN") ?: run {
    println("Please, add your TOKEN in the environment variables")
    ""
}

fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

fun parseWeatherResponse(body: String) = Gson().fromJson(body, WeatherResponse::class.java)

fun parseCoordinatesResponse(body: String): List<Coordinates> {
    val typeToken = object : TypeToken<List<Coordinates>>() {}.type
    return Gson().fromJson(body, typeToken)
}

fun main() {
    val params = mapOf("q" to "Wheel", "limit" to "2", "appid" to TOKEN)
    val urlParams = params.map { (k, v) -> "${(k.utf8())}=${v.utf8()}" }
        .joinToString("&")

    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?$urlParams"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val body = response.body() ?: error("Null body!")
    println(body)
    println(parseCoordinatesResponse(body))
}
