package com.csg.codeit.service

import com.csg.codeit.config.objectMapper
import com.csg.codeit.data.TestCasesProvider
import com.csg.codeit.data.testCases
import com.csg.codeit.model.ChallengeResult
import com.csg.codeit.model.TestCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EvaluatorServiceTest {
    private val webClient = mockk<WebClient>()
    private val checker = mockk<ResultCheckerService>()
    private val testCasesProvider = mockk<TestCasesProvider>()
    private val evaluatorService = EvaluatorServiceImpl(webClient, checker, testCasesProvider)

    companion object {
        private const val TEAM_URL = "team.url.com"
        private val testCase = testCases[0]
    }


    @Test
    fun `can return expected score with multiple test cases`() {
        every { testCasesProvider.getTestCases() }.returns(generateMockTestCases())
        every { webClient.fetchChallengeResponse(testCase.input, TEAM_URL) }
            .returns(testCaseOutputResponseBody())
            .andThen(null)
            .andThen(testCaseOutputResponseBody())
        every { checker.check(testCase.output.toListOfAngles(), testCase) }
            .returns(ChallengeResult(testCase.score, ""))

        val result = evaluatorService.evaluateResult(TEAM_URL)

        assertThat(result.score).isEqualTo(testCase.score * 2)
        verify(exactly = 2) { checker.check(any(), any()) }
        verify(exactly = 3) { webClient.fetchChallengeResponse(any(), any()) }
    }


    private fun generateMockTestCases(): List<TestCase> {
        return listOf(
            testCase,
            testCase,
            testCase,
        )
    }

    private fun testCaseOutputResponseBody(): String = objectMapper.writeValueAsString(testCase.output)
}