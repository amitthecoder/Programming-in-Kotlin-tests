@file:Suppress("COMMENT_WHITE_SPACE")

package jub.test2.api

import kotlinx.serialization.Serializable
import org.jetbrains.kotlin.com.google.gson.annotations.SerializedName

@Serializable
data class Coordinates(
    val lat: Double,
    val lon: Double,
)

@Serializable
data class Weather(
    var temp: Double, // response[main][temp] !!! NEED TO CONVERT TO CELSIUS
    @SerializedName("feels_like")
    var feelsLike: Double, // response[main][feels_like] !!! NEED TO CONVERT TO CELSIUS
    val pressure: Double, // response[main][pressure]
    val humidity: Double, // response[main][humidity]
)

@Serializable
data class Wind(
    val speed: Double, // response[wind][speed]
    val deg: Double, // response[wind][deg]
)

@Serializable
data class WeatherResponse(
    @SerializedName("main")
    val weather: Weather, // response[weather]
    val visibility: Double, // response[visibility]
    val wind: Wind, // response[wind]
)
