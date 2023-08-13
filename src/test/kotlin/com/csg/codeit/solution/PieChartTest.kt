package com.csg.codeit.solution

import PieChart
import com.csg.codeit.data.testCasesForPart1
import com.csg.codeit.service.ResultCheckerService
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class PieChartTest {

    private val solver = PieChart()

    private val resultCheckerService = ResultCheckerService()


    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `can handle test case number`(index: Int) {
        val actual = solver.calculateCoordinates(testCasesForPart1[index].input.data).toListOfAngles()
        val result = resultCheckerService.check(actual, testCasesForPart1[index])
        assertEquals(testCasesForPart1[index].score, result.score)

    }

}