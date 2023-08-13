import com.csg.codeit.data.testCasesForPart2
import com.csg.codeit.model.Instrument
import com.csg.codeit.model.OutputPart2
import com.csg.codeit.solution.AngleCalculator

class ChordDiagram {

    fun calculateCoordinates(securities: List<Instrument>): OutputPart2 {
        val values = securities.map { it.price * it.quantity }
        val gap = Math.PI * 0.001
        val eachSideAngle = (2 * Math.PI / 3)
        val maxAngleForCategory = ((2 * Math.PI / 3) - (3 * gap)) / 4
        val distance = Math.PI / 3

        val minInstrumentShare = 2 * Math.PI * AngleCalculator.defaultMinShare / eachSideAngle
        val instruments = AngleCalculator(maxPossibleAngle = eachSideAngle, minPossibleShare = minInstrumentShare).toRadians(values).sorted()
        val sumValues: (Any, Double?, Instrument, Boolean) -> Double = { _, accumulator, element, _ ->
            with(element) {
                (accumulator ?: 0.0).plus(quantity * price)
            }
        }
        val securitiesByAssetClass = securities.groupingBy { it.assetClass }.aggregate(sumValues)
        val securitiesByCurrency = securities.groupingBy { it.currency }.aggregate(sumValues)
        val securitiesBySector = securities.groupingBy { it.sector }.aggregate(sumValues)
        val securitiesByRegion = securities.groupingBy { it.region }.aggregate(sumValues)
        val minPossibleShare = 2 * Math.PI * AngleCalculator.defaultMinShare / (maxAngleForCategory)
        val assetClass = AngleCalculator(maxPossibleAngle = maxAngleForCategory, minPossibleShare = minPossibleShare).toRadians(securitiesByAssetClass.values.toList()).sortedDescending()
        val currency = AngleCalculator(maxPossibleAngle = maxAngleForCategory, minPossibleShare = minPossibleShare).toRadians(securitiesByCurrency.values.toList()).sortedDescending()
        val sector = AngleCalculator(maxPossibleAngle = maxAngleForCategory, minPossibleShare = minPossibleShare).toRadians(securitiesBySector.values.toList()).sortedDescending()
        val regions = AngleCalculator(maxPossibleAngle = maxAngleForCategory, minPossibleShare = minPossibleShare).toRadians(securitiesByRegion.values.toList()).sortedDescending()
        var accum = distance / 2
        val resultCurrency = (listOf(0.0) + currency).map {
            val res = it + accum
            accum += it
            res
        }
        accum += gap
        val resultSector = (listOf(0.0) + sector).map {
            val res = it + accum
            accum += it
            res
        }
        accum += gap
        val resultAssetClass = (listOf(0.0) + assetClass).map {
            val res = it + accum
            accum += it
            res
        }
        accum += gap
        val resultRegions = (listOf(0.0) + regions).map {
            val res = it + accum
            accum += it
            res
        }
        accum += distance
        val resultInstrument = (listOf(0.0) + instruments).map {
            val res = it + accum
            accum += it
            res
        }

        return OutputPart2(
            resultInstrument,
            resultAssetClass,
            resultCurrency,
            resultSector,
            resultRegions
        )
    }

}

