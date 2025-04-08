import com.example.core.util.weather.WeatherFormatter
import org.junit.Test
import kotlin.test.assertEquals

class WeatherFormatterTest {

    @Test
    fun `formatTemperature converts Kelvin to Celsius with exactly one decimal place`() {
        // Test cases with input (Kelvin) and expected output
        val testCases = listOf(
            273.15 to "0.0°C",
            300.0 to "26.9°C",
            310.15 to "37.0°C",
            0.0 to "-273.2°C",
            500.0 to "226.9°C",
            100.0 to "-173.2°C"
        )

        testCases.forEach { (input, expected) ->
            assertEquals(expected, WeatherFormatter.formatTemperature(input))
        }
    }

    @Test
    fun `formatTemperature shows consistent decimal formatting`() {
        // Verify the decimal is always shown
        assertEquals("0.0°C", WeatherFormatter.formatTemperature(273.2))
        assertEquals("37.0°C", WeatherFormatter.formatTemperature(310.15))
        assertEquals("20.0°C", WeatherFormatter.formatTemperature(293.15))
    }
}