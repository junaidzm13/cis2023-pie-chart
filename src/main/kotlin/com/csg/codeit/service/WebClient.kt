package com.csg.codeit.service

import com.csg.codeit.model.EvaluationResultRequest
import okhttp3.Response

interface WebClient {
    fun postEvalResult(evaluationResultRequest: EvaluationResultRequest, url: String): Response?
    fun fetchChallengeResponse(payload: Any, url: String): String?
}