package com.csg.codeit.model

data class ChallengeResult(val score: Int = 0, val message: String = "") {
    operator fun plus(another: ChallengeResult) =
        copy(score = score + another.score, message = "$message\n${another.message}".trim())
}