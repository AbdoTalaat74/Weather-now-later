package com.example.data.repository
import com.example.core.model.CityForecast
import com.example.core.util.network.NetworkError
import com.example.data.remote.RemoteCityWeather
import com.example.data.remote.WeatherApi
import com.example.data.remote.toCityForecast
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import com.example.core.util.network.Result


class ForecastRepositoryImplTest {

    private lateinit var repository: ForecastRepositoryImpl
    private val weatherApi: WeatherApi = mockk()

    @Before
    fun setUp() {
        repository = ForecastRepositoryImpl(weatherApi)
    }

    @Test
    fun `getCityForecast returns success when API returns 200 and valid body`() = runBlocking {
        // Given
        val cityName = "London"
        val remoteCityWeather = mockk<RemoteCityWeather>()
        val expectedForecast = mockk<CityForecast>()

        coEvery { remoteCityWeather.toCityForecast() } returns expectedForecast
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.success(remoteCityWeather)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Success(expectedForecast), result)
    }

    @Test
    fun `getCityForecast returns serialization error when API returns 200 but null body`() = runBlocking {
        // Given
        val cityName = "London"
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.success(null)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.SERIALIZATION), result)
    }

    @Test
    fun `getCityForecast returns serialization error when body parsing fails`() = runBlocking {
        // Given
        val cityName = "London"
        val remoteCityWeather = mockk<RemoteCityWeather>()

        coEvery { remoteCityWeather.toCityForecast() } throws Exception("Parsing failed")
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.success(remoteCityWeather)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.SERIALIZATION), result)
    }

    @Test
    fun `getCityForecast returns request timeout when API returns 408`() = runBlocking {
        // Given
        val cityName = "London"
        val errorBody = mockk<ResponseBody>()
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.error(408, errorBody)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.REQUEST_TIMEOUT), result)
    }

    @Test
    fun `getCityForecast returns too many requests when API returns 429`() = runBlocking {
        // Given
        val cityName = "London"
        val errorBody = mockk<ResponseBody>()
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.error(429, errorBody)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.TOO_MANY_REQUESTS), result)
    }

    @Test
    fun `getCityForecast returns server error when API returns 500-599`() = runBlocking {
        // Given
        val cityName = "London"
        val errorBody = mockk<ResponseBody>()
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.error(500, errorBody)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.SERVER_ERROR), result)
    }

    @Test
    fun `getCityForecast returns unknown error for other status codes`() = runBlocking {
        // Given
        val cityName = "London"
        val errorBody = mockk<ResponseBody>()
        coEvery { weatherApi.getCityWeather(cityName) } returns Response.error(400, errorBody)

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.UNKNOWN_ERROR), result)
    }

    @Test
    fun `getCityForecast returns no internet connection on IOException`() = runBlocking {
        // Given
        val cityName = "London"
        coEvery { weatherApi.getCityWeather(cityName) } throws IOException()

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.NO_INTERNET_CONNECTION), result)
    }

    @Test
    fun `getCityForecast returns request timeout on SocketTimeoutException`() = runBlocking {
        // Given
        val cityName = "London"
        coEvery { weatherApi.getCityWeather(cityName) } throws SocketTimeoutException()

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.REQUEST_TIMEOUT), result)
    }

    @Test
    fun `getCityForecast returns unknown error on other exceptions`() = runBlocking {
        // Given
        val cityName = "London"
        coEvery { weatherApi.getCityWeather(cityName) } throws Exception()

        // When
        val result = repository.getCityForecast(cityName)

        // Then
        assertEquals(Result.Error(NetworkError.UNKNOWN_ERROR), result)
    }
}