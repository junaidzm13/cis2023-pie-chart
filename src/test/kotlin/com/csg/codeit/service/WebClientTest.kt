package com.csg.codeit.service

import com.csg.codeit.config.AppConfig
import com.csg.codeit.config.objectMapper
import com.csg.codeit.data.hardCodedTestCasesPart1
import com.csg.codeit.model.EvaluationResultRequest
import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WebClientTest {

    private val httpClient = OkHttpClient()
    private val appConfig = mockk<AppConfig>()
    private val webClient = EvalWebClient(httpClient, appConfig)

    private val mockWebServer = MockWebServer()
    private val mockServerUrl = mockWebServer.url("").toString()

    @BeforeEach
    fun setUp() {
        every { appConfig.endpointSuffix }.returns("endpoint-suffix")
        every { appConfig.coordinatorAuthToken }.returns("auth-token")
    }

    @Test
    fun `fetchChallengeResponse can send expected request and return response body`() {
        val testCase = hardCodedTestCasesPart1[0]
        val responseBody = objectMapper.writeValueAsString(testCase.output)
        val mockResponse = MockResponse().setResponseCode(200).setBody(responseBody)
        mockWebServer.enqueue(mockResponse)

        val result = webClient.fetchChallengeResponse(testCase.input, mockServerUrl)
        assertThat(result).isEqualTo(responseBody)
        val request = mockWebServer.takeRequest()

        assertThat(request.requestUrl.toString()).isEqualTo(mockServerUrl + appConfig.endpointSuffix)
        assertThat(request.method).isEqualTo("POST")
    }

    @Test
    fun `postEvalResult can send expected request`() {
        val evaluationResultRequest = EvaluationResultRequest("RUN_ID", 90, "some-msg")
        val mockResponseBody = objectMapper.writeValueAsString(evaluationResultRequest)
        val mockResponse = MockResponse().setResponseCode(200).setBody(mockResponseBody)
        mockWebServer.enqueue(mockResponse)

        webClient.postEvalResult(evaluationResultRequest, mockServerUrl)
        val request = mockWebServer.takeRequest()

        assertThat(request.requestUrl.toString()).isEqualTo(mockServerUrl)
        assertThat(request.method).isEqualTo("POST")
        assertThat(request.getHeader("Authorization")).isEqualTo("Bearer " + appConfig.coordinatorAuthToken)
        assertThat(request.body.toString()).contains("text=$mockResponseBody")
    }
}