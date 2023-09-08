package com.csg.codeit.service

import com.csg.codeit.model.ChallengeResult
import com.csg.codeit.model.TestCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class ResultCheckerService {
    companion object {
        private const val ERROR_THRESHOLD = 0.000001 // 10 ^ -6
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun check(actual: List<Double>, testCase: TestCase): ChallengeResult {
        val expected = testCase.output.toListOfAngles()

        if (actual.size != expected.size) {
            return ChallengeResult(0, "Incorrect number of angles")
        }

        val outsideOfThreshold = actual.filterIndexed {
            index, angle -> !withinThreshold(angle, expected[index])
        }

        return if (outsideOfThreshold.isEmpty()) {

            ChallengeResult(testCase.score, "${testCase.input.part} part: test case correct!")
        } else {
            outsideOfThreshold.forEach {
                logger.error("[INCORRECT VALUES]. Expected: $expected. Actual: $actual")
            }
            ChallengeResult(0, "${testCase.input.part} part: Incorrect angle values")
        }
    }

    private fun withinThreshold(angle1: Double, angle2: Double): Boolean {
        return abs(angle1 - angle2) < ERROR_THRESHOLD
    }
}