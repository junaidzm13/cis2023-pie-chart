package com.csg.codeit.solution

import PieChart
import com.csg.codeit.data.hardCodedTestCasesPart1
import com.csg.codeit.service.ResultCheckerService
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class PieChartTest {

    private val solver = PieChart()

    private val resultCheckerService = ResultCheckerService()


    @ParameterizedTest
    @MethodSource("testCaseIndicesProvider")
    fun `can handle test case number`(index: Int) {
        val actual = solver.calculateCoordinates(hardCodedTestCasesPart1[index].input.data).toListOfAngles()
        val result = resultCheckerService.check(actual, hardCodedTestCasesPart1[index])
        assertEquals(hardCodedTestCasesPart1[index].score, result.score)
    }

    companion object {
        @JvmStatic
        private fun testCaseIndicesProvider() = (hardCodedTestCasesPart1.indices).toList()
    }

}