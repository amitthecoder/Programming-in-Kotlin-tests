// Please, delete it after adding your implementation
@file:Suppress("UnusedPrivateMember")

package jub.test2.api

object QueryHandler {
    fun getCityCoordinates(city: String, appKey: String, limit: Int = 5): List<Coordinates> = TODO()

    fun getWeather(coordinates: Coordinates, appKey: String): WeatherResponse = TODO()
}
