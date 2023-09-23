package com.csg.codeit.data

import com.csg.codeit.model.Part
import com.csg.codeit.model.TestCase
import org.springframework.stereotype.Component

@Component
class TestCasesProvider {
    fun getTestCases(): List<TestCase> =
        // PART 1
        hardCodedTestCasesPart1.subList(2, 4) +
        createRandomTestCase(Part.FIRST, 50, 100) +
        listOf(1000, 1500, 1850, 1900, 1900, 1975).map(::create2StepReBalancingRandomTCP1) +
        createRandomTestCase(Part.FIRST, 2000) +
        // PART 2
        createRandomTestCase(Part.SECOND, 20, 100) +
        hardCodedTestCasesPart2.subList(1, 2) +
        listOf(50, 250, 500, 600, 600, 640, 640).map(::create2StepReBalancingRandomTCP2) +
        createRandomTestCase(Part.SECOND, 666, 666)
}