package com.csg.codeit.solution

import com.csg.codeit.model.Instrument
import com.csg.codeit.model.Output

interface Solver {
    fun calculateCoordinates(securities: List<Instrument>): Output
}