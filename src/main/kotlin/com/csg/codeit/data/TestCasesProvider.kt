package com.csg.codeit.data

import com.csg.codeit.model.Part
import com.csg.codeit.model.TestCase
import org.springframework.stereotype.Component

@Component
class TestCasesProvider {
    fun getTestCases(): List<TestCase> =
        // PART 1
        hardCodedTestCasesPart1.subList(2, 4) +
        createRandomTestCase(Part.FIRST, 5, 15) +
        listOf(50, 100, 500, 1000, 1000, 1500).map(::create2StepReBalancingRandomTCP1) +
        createRandomTestCase(Part.FIRST, 2000) +
        // PART 2
        createRandomTestCase(Part.SECOND, 15, 25) +
        hardCodedTestCasesPart2.subList(1, 2) +
        listOf(50, 100, 200, 400, 500, 500, 565).map(::create2StepReBalancingRandomTCP2) +
        createRandomTestCase(Part.SECOND, 666, 666)
}