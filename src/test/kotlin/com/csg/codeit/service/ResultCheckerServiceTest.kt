package com.csg.codeit.service

import com.csg.codeit.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResultCheckerServiceTest {

    private val resultCheckerService = ResultCheckerService()

    @Test
    fun `when size of angles returned doesn't match expected then return 0 score`() {
        val result = resultCheckerService.check(
            listOf(3.0, 5.0),
            TestCase(
                input = Input(data = listOf(), part = Part.FIRST),
                output = OutputPart1(instruments = listOf(3.0, 5.0, 6.0)),
                score=4
            )
        )

        assertThat(result.score).isEqualTo(0)
        assertThat(result.message).contains("number of", "angles")
    }

    @Test
    fun `when at least one of angles returned aren't within threshold then return score of 0`() {
        val result = resultCheckerService.check(
            listOf(3.0, 5.005),
            TestCase(
                input = Input(data = listOf(), part = Part.FIRST),
                output = OutputPart1(instruments = listOf(3.0, 5.01)),
                score=4
            )
        )

        assertThat(result.score).isEqualTo(0)
        assertThat(result.message).contains("Incorrect", "values")
    }

    @Test
    fun `when all returned angles are within threshold then return score equal to the testcase score`() {
        val result = resultCheckerService.check(
            listOf(3.0, 5.0001),
            TestCase(
                input = Input(data = listOf(), part = Part.FIRST),
                output = OutputPart1(instruments = listOf(3.0, 5.000105)),
                score=143
            )
        )

        assertThat(result.score).isEqualTo(143)
        assertThat(result.message).contains("FIRST part: test case correct")
    }
}