package com.backbase.assignment
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.backbase.assignment.ui.ui.dashboard.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DashboardViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var mainViewModel: DashboardViewModel

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainViewModel = mock(DashboardViewModel::class.java)
    }
    @Test
    fun getAllMoviesTest() {
        runBlocking {
//            Mockito.`when`().thenReturn(PlayingNowResponse(null,1,
//                emptyList(),1,1))
//            val result = mainViewModel.getPlayingNowResponse(1)
//            assertNotSame(PlayingNowResponse(null,1, emptyList(),10,10 ), result)

        }
    }
}