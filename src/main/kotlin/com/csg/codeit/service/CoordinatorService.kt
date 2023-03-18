package com.csg.codeit.service

import com.csg.codeit.config.AppConfig
import com.csg.codeit.config.objectMapper
import com.csg.codeit.model.ChallengeRequest
import com.csg.codeit.model.ChallengeResponse
import com.csg.codeit.model.ChallengeRun
import com.csg.codeit.model.EvaluationRequest
import com.csg.codeit.model.EvaluationResultRequest
import com.csg.codeit.model.EvaluatorService
import com.csg.codeit.model.RequestPayload
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CoordinatorService(
    val appConfig: AppConfig,
    val httpClient: OkHttpClient,
    val evaluatorService: EvaluatorService
) {
    private val logger: Logger = LoggerFactory.getLogger(CoordinatorService::class.java)

    operator fun invoke(evaluationRequest: EvaluationRequest) {
        val result = evaluatorService.evaluateTeam(evaluationRequest.asRun)
            .let { EvaluationResultRequest(evaluationRequest.runId, it.score, it.message) }
        try {
            result.post(evaluationRequest.callbackUrl.toHttpUrl()) {
                it.addHeader("Authorization", appConfig.bearerToken)
            }?.also { logger.info("Notified coordinator with: $result") }
                ?: logger.warn("Error notifying coordinator with: $result")
        } catch (e: Exception) {
            logger.error("Error notifying coordinator with: $result\nException message: ${e.message}")
        }
    }

    private val EvaluationRequest.asRun: ChallengeRun
        get() = object : ChallengeRun {
            override val teamUrl: String = this@asRun.teamUrl

            override fun invoke(challengeRequest: ChallengeRequest): ChallengeResponse? =
                challengeRequest.also { logger.debug("Evaluating $runId with: ${it.toJson}") }
                    .post(teamUrl.toHttpUrl().newBuilder().addPathSegment(appConfig.endpointSuffix).build())
                    ?.use { it.body?.string()?.let(evaluatorService::convert) }
                    ?.also { logger.debug("Got response for $runId: $it") }
        }

    private val AppConfig.bearerToken: String get() = "Bearer $coordinatorAuthToken"

    private val RequestPayload.toJson: String get() = objectMapper.writeValueAsString(this)

    private fun RequestPayload.post(url: HttpUrl, modifier: (Request.Builder) -> Request.Builder = { it }): Response? =
        Request.Builder().post(this.toJson.toRequestBody(MEDIA_TYPE_JSON))
            .let { httpClient.newCall(it.url(url).let(modifier).build()).execute() }
            .let { if (it.isSuccessful) it else it.close().run { null } }

    companion object {
        private val MEDIA_TYPE_JSON = "application/json".toMediaType()
    }
}
