import com.csg.codeit.model.Instrument
import com.csg.codeit.model.OutputPart1
import com.csg.codeit.solution.AngleCalculator
import com.csg.codeit.solution.Solver


class PieChart: Solver {
    override fun calculateCoordinates(securities: List<Instrument>): OutputPart1 {
        val sortedValues = securities.map { it.price * it.quantity }.sorted()
        val angles = AngleCalculator().toRadians(sortedValues).sortedDescending()

        var accum = 0.0
        val instruments = (listOf(0.0) + angles).map {
            val res = it + accum
            accum += it
            res
        }

        return OutputPart1(
            instruments
        )

    }

}