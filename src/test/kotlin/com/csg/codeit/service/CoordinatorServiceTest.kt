package com.csg.codeit.service

import com.csg.codeit.model.ChallengeResult
import com.csg.codeit.model.EvaluationRequest
import com.csg.codeit.model.EvaluationResultRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class CoordinatorServiceTest {

    private val evaluatorService = mockk<EvaluatorService>()
    private val webClient = mockk<WebClient>(relaxed = true)
    private val coordinatorService = CoordinatorService(evaluatorService, webClient)

    companion object {
        private const val CALLBACK_URL = "call-back-url.com"
        private const val TEAM_URL = "team-url.com"
        private const val RUN_ID = "run-id"
        private const val MESSAGE = "mock result message"
        private const val SCORE = 100
    }

    @Test
    fun `can call evaluateResult and post the result to the passed callBackUrl`() {
        every { evaluatorService.evaluateResult(TEAM_URL) }.returns(ChallengeResult(SCORE, MESSAGE))

        coordinatorService(EvaluationRequest(RUN_ID, TEAM_URL, CALLBACK_URL))

        verify(exactly = 1) { webClient.postEvalResult(
            EvaluationResultRequest(RUN_ID, SCORE, MESSAGE),
            CALLBACK_URL
        ) }
    }

}