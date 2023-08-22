package com.csg.codeit.solution

import ChordDiagram
import com.csg.codeit.data.testCasesForPart2
import com.csg.codeit.model.TestCase
import com.csg.codeit.service.ResultCheckerService
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class ChordDiagramTest {

    private val solver = ChordDiagram()

    private val resultCheckerService = ResultCheckerService()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `can handle test case number`(testCase: TestCase) {
        val actual = solver.calculateCoordinates(testCase.input.data).toListOfAngles()
        val checkedResult = resultCheckerService.check(actual, testCase)
        assertEquals(testCase.score, checkedResult.score)
    }

    companion object {
        @JvmStatic
        private fun testCases() = testCasesForPart2.map { Arguments.of(it) }.stream()
    }

}