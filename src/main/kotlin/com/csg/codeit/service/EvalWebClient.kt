package com.csg.codeit.service

import com.csg.codeit.config.AppConfig
import com.csg.codeit.config.objectMapper
import com.csg.codeit.model.EvaluationResultRequest
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.stereotype.Service

@Service
class EvalWebClient(
    private val httpClient: OkHttpClient,
    private val appConfig: AppConfig
): WebClient {

    companion object {
        val MEDIA_TYPE_JSON = "application/json".toMediaType()
    }

    override fun postEvalResult(evaluationResultRequest: EvaluationResultRequest, url: String): Response? {
        return Request.Builder()
            .post(toRequestBody(evaluationResultRequest))
            .addHeader("Authorization", appConfig.bearerToken)
            .url(url.toHttpUrl())
            .build()
            .let { makePostRequest(it) }
    }

    override fun fetchChallengeResponse(payload: Any, url: String): String? {
        return Request.Builder()
            .post(toRequestBody(payload))
            .url(url.toHttpUrl().newBuilder().addPathSegment(appConfig.endpointSuffix).build())
            .build()
            .let { makePostRequest(it)?.body?.string() }
    }

    private fun makePostRequest(request: Request): Response? {
        return httpClient.newCall(request).execute()
            .let { if (it.isSuccessful) it else it.close().run { null } }
    }

    private fun <T> toRequestBody(value: T): RequestBody = objectMapper.writeValueAsString(value).toRequestBody(MEDIA_TYPE_JSON)

    private val AppConfig.bearerToken: String get() = "Bearer $coordinatorAuthToken"
}