package com.csg.codeit.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OutputTest {

    @Test
    fun `OutputPart1 correctly converts to list of angles`() {
        val listOfInstrumentAngles = listOf(1.00, 4.00, 3.00, 7.00)

        val outputPart1 = OutputPart1(instruments = listOfInstrumentAngles)

        assertThat(outputPart1.toListOfAngles()).isEqualTo(listOfInstrumentAngles)
    }

    @Test
    fun `OutputPart2 correctly converts to list of angles`() {
        val outputPart2 = OutputPart2(
            instruments = listOf(5.0, 3.0),
            assetClass = listOf(1.0, 1.5),
            currency = listOf(2.0),
            sector = listOf(2.85, 2.90),
            region = listOf(2.25, 2.70, 2.80)
        )

        val result = outputPart2.toListOfAngles()

        assertThat(result[0]).isEqualTo(5.0)
        assertThat(result[result.size - 1]).isEqualTo(2.80)
    }
}