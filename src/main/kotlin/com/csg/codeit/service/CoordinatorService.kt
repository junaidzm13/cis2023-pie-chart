package com.csg.codeit.service

import com.csg.codeit.model.EvaluationRequest
import com.csg.codeit.model.EvaluationResultRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CoordinatorService(
    val evaluatorService: EvaluatorService,
    val webClient: WebClient
) {
    private val logger: Logger = LoggerFactory.getLogger(CoordinatorService::class.java)

    operator fun invoke(evaluationRequest: EvaluationRequest) {
        val result = evaluatorService.evaluateResult(evaluationRequest.teamUrl)
            .let { EvaluationResultRequest(evaluationRequest.runId, it.score, it.message) }
        try {
            webClient.postEvalResult(result, evaluationRequest.callbackUrl)
                ?.also { logger.info("Notified coordinator with: $result") }
                ?: logger.warn("Error notifying coordinator with: $result")
        } catch (e: Exception) {
            logger.error("Error notifying coordinator with: $result\nException message: ${e.message}")
        }
    }
}
