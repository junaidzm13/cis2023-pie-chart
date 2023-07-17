package com.csg.codeit.service

import com.csg.codeit.model.ChallengeResult
import com.csg.codeit.model.TestCase
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class ResultCheckerService {
    companion object {
        private const val ERROR_THRESHOLD = 0.00001 // 10 ^ -5
    }

    fun check(actual: List<Double>, testCase: TestCase): ChallengeResult {
        val expected = testCase.output.toListOfAngles()

        if (actual.size != expected.size) {
            return ChallengeResult(0, "Incorrect number of angles")
        }

        return actual.filterIndexed {
            index, angle -> !withinThreshold(angle, expected[index])
        }.size.let {
            if (it == 0) ChallengeResult(testCase.score, "Correct!")
            else ChallengeResult(0, "Incorrect angle values")
        }
    }

    private fun withinThreshold(angle1: Double, angle2: Double): Boolean {
        return abs(angle1 - angle2) < ERROR_THRESHOLD
    }
}