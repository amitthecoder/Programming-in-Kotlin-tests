import jub.test2.api.Coordinates
import jub.test2.api.QueryHandler
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ApiRequestsTests {

    companion object {
        var TOKEN: String = System.getenv("TOKEN") ?: run {
            println("TOKEN env variable was not found!")
            error("Please, add your TOKEN in the environment variables")
        }

        @JvmStatic
        fun cities() = listOf(
            Arguments.of("London", 5),
            Arguments.of("Toronto", 3),
            Arguments.of("Wheel", 2),
            Arguments.of("Berlin", 5),
            Arguments.of("Boston", 2),
        )

        // NY
        private val baseCoordinates = Coordinates(40.7127281, -74.0060152)

        @JvmStatic
        fun coordinates() = listOf(
            Arguments.of(Coordinates(51.5073219, -0.1276474)),
            Arguments.of(Coordinates(43.6534817, -79.3839347)),
            Arguments.of(Coordinates(36.8556118, -88.8036665)),
            Arguments.of(Coordinates(52.5170365, 13.3888599)),
            Arguments.of(Coordinates(42.355434, -71.06051)),
        )
    }

    @ParameterizedTest
    @MethodSource("cities")
    fun coordinatesRequestTest(city: String, limit: Int) {
        val coordinates = QueryHandler.getCityCoordinates(city, TOKEN, limit)
        assert(coordinates.size == limit) { "You need to get $limit coordinates for a query with arguments: city: $city and limit: $limit" }
    }

    @ParameterizedTest
    @MethodSource("coordinates")
    fun weatherRequestTest(coordinates: Coordinates) {
        val weatherResponse = QueryHandler.getWeather(coordinates, TOKEN)
        val otherWeatherResponse = QueryHandler.getWeather(baseCoordinates, TOKEN)
        assert(weatherResponse != otherWeatherResponse) { "You need to get different responses for the different cities" }
        assert(weatherResponse.weather.temp >= -50 && weatherResponse.weather.temp <= 50) { "You need to convert the weather temperature to Celsius" }
    }
}
