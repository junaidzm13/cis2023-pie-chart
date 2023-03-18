package com.csg.codeit.service

import com.csg.codeit.model.ChallengeLevel
import com.csg.codeit.model.ChallengeResult
import com.csg.codeit.model.ChallengeRun
import com.csg.codeit.model.Checker
import com.csg.codeit.model.EvaluatorService
import com.csg.codeit.model.LevelBasedChallenge
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LevelBasedEvaluatorService(
    override val challenge: LevelBasedChallenge,
    override val checker: Checker,
    val levels: Iterable<ChallengeLevel>
) : EvaluatorService {
    private val logger: Logger = LoggerFactory.getLogger(LevelBasedEvaluatorService::class.java)

    override fun evaluateTeam(challengeRun: ChallengeRun): ChallengeResult = levels.mapNotNull { level ->
        try {
            with(challenge.createFor(challengeRun.teamUrl, level)) {
                challengeRun(this)?.let { checker.check(this, it) }
            }
        } catch (e: Exception) {
            logger.error(e.message)
            null
        }
    }.fold(ChallengeResult(), ChallengeResult::plus)
}
