package com.csg.codeit.solution

class AngleCalculator(
    private val minPossibleShare: Double = defaultMinShare,
    private val maxPossibleAngle: Double = 2 * Math.PI
) {

    companion object {
        const val defaultMinShare: Double = 0.0005
    }

    fun toRadians(values: List<Double>): List<Double> {
        var outliersCount = 0
        val minAngle = minPossibleShare * maxPossibleAngle
        var remainingValue = values.sum()
        return values.sorted().map { securityValue ->
            val angle = (securityValue / remainingValue) * (maxPossibleAngle * (1 - outliersCount * minPossibleShare))
            if (angle < minAngle) {
                outliersCount++
                remainingValue -= securityValue
            }
            angle.coerceAtLeast(minAngle)
        }

    }
}