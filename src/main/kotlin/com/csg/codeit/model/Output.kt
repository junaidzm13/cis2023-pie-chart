package com.csg.codeit.model

interface Output {
    fun toListOfAngles(): List<Double>;
}

data class OutputPart1(
    val instruments: List<Double>
): Output {
    override fun toListOfAngles() = instruments
}

data class OutputPart2(
    val instruments: List<Double>,
    val assetClass: List<Double>,
    val currency: List<Double>,
    val sector: List<Double>,
    val region: List<Double>
): Output {
    override fun toListOfAngles() = listOf(
        instruments,
        assetClass,
        currency,
        sector,
        region
    ).flatten()
}