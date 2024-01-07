package com.example.countryapplication.viewmodel

import com.example.countryapplication.data.CountryRepository
import com.example.countryapplication.model.Car
import com.example.countryapplication.model.CoatOfArms
import com.example.countryapplication.model.Country
import com.example.countryapplication.model.Currency
import com.example.countryapplication.model.Flags
import com.example.countryapplication.model.Idd
import com.example.countryapplication.model.Name
import com.example.countryapplication.model.NativeName
import com.example.countryapplication.ui.country.CountryDetailApiState
import com.example.countryapplication.ui.country.CountryDetailState
import com.example.countryapplication.ui.country.CountryDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test suite for [CountryDetailViewModel] responsible for handling country detail related data and UI states.
 */
@ExperimentalCoroutinesApi
class CountryDetailViewModelTest {

    private lateinit var viewModel: CountryDetailViewModel
    private var countryRepository: CountryRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test to verify that 'getCountry' updates UI state and sets 'countryDetailApiState' to Success.
     */
    @Test
    fun `getCountry should update uiState and set countryDetailApiState to Success`() =
        testScope.runTest {

            // Arrange
            val expectedCountry =
                Country(
                    name = Name(
                        common = "Belgium",
                        official = "Kingdom of Belgium",
                        nativeName = mapOf(
                            "deu" to NativeName(
                                official = "Königreich Belgien",
                                common = "Belgien",
                            ),
                            "fra" to NativeName(
                                official = "Royaume de Belgique",
                                common = "Royaume de Belgique",
                            ),
                            "nld" to NativeName(
                                official = "Koninkrijk België",
                                common = "België",
                            ),
                        ),
                    ),
                    area = 30528.0,
                    population = 11555997,
                    capital = listOf("Brussels"),
                    unMember = true,
                    independent = true,
                    flags = Flags(
                        png = "https://flagcdn.com/w320/be.png",
                        svg = "https://flagcdn.com/be.svg",
                        alt = "The flag of Belgium is composed of three equal vertical bands of black, yellow and red.",
                    ),
                    car = Car(
                        signs = listOf("B"),
                        side = "right",
                    ),
                    coatOfArms = CoatOfArms(
                        png = "https://mainfacts.com/media/images/coats_of_arms/be.png",
                        svg = "https://mainfacts.com/media/images/coats_of_arms/be.svg",
                    ),
                    currencies = mapOf(
                        "EUR" to Currency(
                            name = "Euro",
                            symbol = "€",
                        ),
                    ),
                    idd = Idd(
                        root = "+3",
                        suffixes = listOf("2"),
                    ),
                    region = "Europe",
                    languages = mapOf(
                        "deu" to "German",
                        "fra" to "French",
                        "nld" to "Dutch",
                    ),
                    timezones = listOf("UTC+01:00"),
                    tld = listOf(".be"),
                )

            coEvery { countryRepository.getCountry("Kingdom of Belgium") } returns flowOf(expectedCountry)

            coEvery { countryRepository.refresh() } returns Unit

            viewModel = CountryDetailViewModel(countryRepository)

            // Act
            viewModel.getCountry("Kingdom of Belgium")

            val collectCompleted = CompletableDeferred<Unit>()

            // Act
            var uiCountryDetailState: CountryDetailState? = null
            val job = launch {
                viewModel.uiState.collect {
                    uiCountryDetailState = it
                    collectCompleted.complete(Unit)
                }
            }

            // Wait for the collect block to complete
            collectCompleted.await()

            // Ensure the asynchronous operations are completed
            advanceUntilIdle()

            // Assert
            assert(viewModel.countryDetailApiState is CountryDetailApiState.Success)
            assert(uiCountryDetailState == CountryDetailState(expectedCountry))

            // Cancel the job to avoid potential leaks
            job.cancel()
        }
}
