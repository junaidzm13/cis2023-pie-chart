package com.csg.codeit.solution

import ChordDiagram
import com.csg.codeit.data.testCasesForPart2
import com.csg.codeit.service.ResultCheckerService
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class ChordDiagramTest {

    private val solver = ChordDiagram()

    private val resultCheckerService = ResultCheckerService()

    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `can handle test case number`(index: Int) {
        val actual = solver.calculateCoordinates(testCasesForPart2[index].input.data).toListOfAngles()
        val checkedResult = resultCheckerService.check(actual, testCasesForPart2[index])
        assertEquals(testCasesForPart2[index].score, checkedResult.score)
    }

}