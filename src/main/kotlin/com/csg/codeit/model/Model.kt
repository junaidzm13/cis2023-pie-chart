package com.csg.codeit.model

interface RequestPayload {
    fun name(): String = toString()
}

interface ChallengeRequest : RequestPayload

interface ChallengeResponse {
    fun name(): String = toString()
}

data class ChallengeResult(val score: Int = 0, val message: String = "") {
    operator fun plus(another: ChallengeResult) =
        copy(score = score + another.score, message = "$message\n${another.message}".trim())
}

interface ChallengeLevel {
    val difficulty: Int
}

interface Challenge {
    fun createFor(teamUrl: String): ChallengeRequest
}

interface LevelBasedChallenge : Challenge {
    fun createFor(teamUrl: String, level: ChallengeLevel): ChallengeRequest
}

interface ChallengeRun : (ChallengeRequest) -> ChallengeResponse? {
    val teamUrl: String
}

interface Checker {
    fun convert(rawResponse: String): ChallengeResponse

    fun check(request: ChallengeRequest, response: ChallengeResponse): ChallengeResult
}

interface EvaluatorService {
    val challenge: Challenge
    val checker: Checker

    fun evaluateTeam(challengeRun: ChallengeRun): ChallengeResult

    fun convert(rawResponse: String): ChallengeResponse = checker.convert(rawResponse)
}

data class EvaluationRequest(val runId: String, val teamUrl: String, val callbackUrl: String)

data class EvaluationResultRequest(val runId: String, val score: Int, val message: String) : RequestPayload
