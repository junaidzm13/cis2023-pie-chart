package com.csg.codeit.data

import com.csg.codeit.model.Part
import com.csg.codeit.model.TestCase
import org.springframework.stereotype.Component

@Component
class TestCasesProvider {
    fun getTestCases(): List<TestCase> =
        // PART 1
        hardCodedTestCasesPart1.subList(1, 4) +
        listOf(50, 100, 500, 1000, 1000, 1500).map(::create2StepReBalancingRandomTCP1) +
        createRandomTestCase(Part.FIRST, 2000) +
        // PART 2 @TODO random case generation to be enhanced to support two-step re-balancing
        hardCodedTestCasesPart2 +
        listOf(50, 100, 200, 300, 400, 500, 600, 666)
            .map { createRandomTestCase(Part.SECOND, it, 666) }
}