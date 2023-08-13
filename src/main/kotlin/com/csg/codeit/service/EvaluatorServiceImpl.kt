package com.csg.codeit.service

import com.csg.codeit.config.objectMapper
import com.csg.codeit.data.TestCasesProvider
import com.csg.codeit.data.testCases
import com.csg.codeit.model.*
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EvaluatorServiceImpl(
    private val webClient: WebClient,
    private val checker: ResultCheckerService,
    private val testCasesProvider: TestCasesProvider
): EvaluatorService {
    private val logger: Logger = LoggerFactory.getLogger(EvaluatorServiceImpl::class.java)

    override fun evaluateResult(teamUrl: String): ChallengeResult {
        val evaluateScore = scoreEvaluator(teamUrl)
        return testCasesProvider.getTestCases().mapNotNull { testCase -> evaluateScore(testCase) }
            .fold(ChallengeResult(), ChallengeResult::plus)
    }

    private fun scoreEvaluator(teamUrl: String): (TestCase) -> ChallengeResult? = {
        try {
            webClient.fetchChallengeResponse(it.input, teamUrl)
                ?.let { rawVal -> readBody(it.input.part)(rawVal) }
                ?.let { actual -> checker.check(actual.toListOfAngles(), it) }
                ?: ChallengeResult(0, "Error sending request to $teamUrl").also {
                    logger.error("[INCORRECT VALUES]. Team url: $teamUrl")
                }
        } catch (e: Exception) {
            logger.error(e.message)
            null
        }
    }

    private fun readBody(part: Part): (String) -> Output = {
        if (part == Part.FIRST) objectMapper.readValue<OutputPart1>(it)
        else objectMapper.readValue<OutputPart2>(it)
    }
}
