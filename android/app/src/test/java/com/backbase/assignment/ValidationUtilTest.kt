package com.backbase.assignment

import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.utils.ValidationUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.*


@RunWith(JUnit4::class)
class ValidationUtilTest {
    @Test
    fun validateMovieEmptyTest() {
        val playingNowResponse = PlayingNowResponse(null,1, emptyList(),10,20)
        assertEquals(true, ValidationUtil.validateMovie(playingNowResponse))
    }

}