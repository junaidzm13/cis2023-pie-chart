package com.csg.codeit.service

import com.csg.codeit.model.ChallengeResult

interface EvaluatorService {
    fun evaluateResult(teamUrl: String): ChallengeResult
}