package com.csg.codeit.data

import ChordDiagram
import PieChart
import com.csg.codeit.model.*
import kotlin.math.ceil
import kotlin.random.Random

val hardCodedTestCasesPart1 = listOf(
    TestCase(
        Input(
            listOf(
                Instrument(
                    4,
                    5.0,
                    Currency.HKD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    4.0,
                    Currency.JPY,
                    Sector.Finance,
                    AssetClass.FixedIncome,
                    Region.APAC
                ),
                Instrument(
                    10,
                    6.0,
                    Currency.EUR,
                    Sector.Education,
                    AssetClass.Derivatives,
                    Region.EMEA
                ),
            ),
            Part.FIRST
        ),
        OutputPart1(listOf(
            0.0,
            Math.PI * 2 * 3 / 5,
            Math.PI * 2 * 4 / 5,
            2 * Math.PI
        )),
        Part.FIRST.score
    ),
    TestCase(
        Input(
            listOf(
                Instrument(
                    110,
                    10.0,
                    Currency.SGD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    1.0,
                    Currency.USD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.NORTH_AMERICA
                ),
                Instrument(
                    39,
                    5.0,
                    Currency.GBP,
                    Sector.Education,
                    AssetClass.Equity,
                    Region.EMEA
                ),
                Instrument(
                    32,
                    100.0,
                    Currency.Other,
                    Sector.Pharmaceutical,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    200,
                    30.0,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.FixedIncome,
                    Region.APAC
                )
            ),
            Part.FIRST
        ),
        OutputPart1(listOf(
            0.0,
            3.59030608,
            5.50513599,
            6.16335877,
            6.28004372,
            2 * Math.PI
        )),
        Part.FIRST.score
    ),
    TestCase( // multiple first step re-balancing
        Input(
            listOf(
                Instrument(
                    30,
                    150.0,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    4,
                    1.5,
                    Currency.HKD,
                    Sector.Other,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    40,
                    275.0,
                    Currency.JPY,
                    Sector.Pharmaceutical,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    2,
                    4.0,
                    Currency.HKD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    35,
                    100.0,
                    Currency.RMB,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    10,
                    0.1,
                    Currency.JPY,
                    Sector.Finance,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    1.0,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    97,
                    10.0,
                    Currency.HKD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                )
            ),
            Part.FIRST
        ),
        OutputPart1(listOf(
            0.0,
            3.45402145,
            4.86703023,
            5.96603706,
            6.27061894,
            6.27376053,
            6.27690212,
            6.28004372,
            6.28318531
        )),
        Part.FIRST.score
    ),
    TestCase( // 2 step re-balancing
        Input(
            listOf(
                Instrument(
                    5,
                    1.0,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    1.0,
                    Currency.HKD,
                    Sector.Other,
                    AssetClass.FixedIncome,
                    Region.APAC
                ),
                Instrument(
                    19,
                    0.1,
                    Currency.SGD,
                    Sector.Other,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    10,
                    0.1,
                    Currency.USD,
                    Sector.Other,
                    AssetClass.Equity,
                    Region.EMEA
                ),
                Instrument(
                    5,
                    0.2,
                    Currency.USD,
                    Sector.Pharmaceutical,
                    AssetClass.Derivatives,
                    Region.NORTH_AMERICA
                ),
                Instrument(
                    20,
                    0.1,
                    Currency.USD,
                    Sector.Education,
                    AssetClass.Equity,
                    Region.NORTH_AMERICA
                ),
                Instrument(
                    2,
                    1.0,
                    Currency.HKD,
                    Sector.Education,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    10,
                    0.2,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    97,
                    100.0,
                    Currency.HKD,
                    Sector.Sports,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    500,
                    5.0,
                    Currency.JPY,
                    Sector.Pharmaceutical,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    30,
                    150.0,
                    Currency.HKD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    100,
                    30.0,
                    Currency.RMB,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    1001,
                    0.01,
                    Currency.GBP,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.EMEA
                ),
                Instrument(
                    51,
                    5.0,
                    Currency.GBP,
                    Sector.Education,
                    AssetClass.Equity,
                    Region.EMEA
                ),
                Instrument(
                    1,
                    15.0,
                    Currency.Other,
                    Sector.Other,
                    AssetClass.RealEstate,
                    Region.EMEA
                )
            ),
            Part.FIRST
        ),
        OutputPart1(listOf(
            0.0,
            3.03818910,
            4.44765827,
            5.38730438,
            6.17034281,
            6.25021273,
            6.25491097,
            6.25805257,
            6.26119416,
            6.26433575,
            6.26747734,
            6.27061894,
            6.27376053,
            6.27690212,
            6.28004372,
            6.28318531
        )),
        Part.FIRST.score
    ),
)


val hardCodedTestCasesPart2 = listOf(
    TestCase(
        Input(
            listOf(
                Instrument(
                    4,
                    5.0,
                    Currency.HKD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    4.0,
                    Currency.JPY,
                    Sector.Finance,
                    AssetClass.FixedIncome,
                    Region.APAC
                ),
                Instrument(
                    10,
                    6.0,
                    Currency.EUR,
                    Sector.Education,
                    AssetClass.Derivatives,
                    Region.EMEA
                ),
            ),
            Part.SECOND
        ),
        OutputPart2(
            instruments = listOf(
                Math.PI * 7 / 6,
                Math.PI * 7 / 6 + Math.PI * 2 / 15,
                Math.PI * 7 / 6 + Math.PI * 4 / 15,
                Math.PI * 7 / 6 + Math.PI * 2 / 3
            ),
            currency = listOf(
                Math.PI * 1 / 6,
                0.83634432,
                0.94059284,
                1.04484136,
            ),
            sector = listOf(
                1.04798295,
                1.36072850,
                1.46497701,
                1.56922553
            ),
            assetClass = listOf(
                1.57236712,
                1.88511267,
                1.98936119,
                2.09360970
            ),
            region = listOf(
                2.09675130,
                2.40949684,
                2.61799388
            )
        ),
        Part.SECOND.score
    ),
    TestCase(
        Input(
            listOf(
                Instrument(
                    110,
                    10.0,
                    Currency.SGD,
                    Sector.ECommerce,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    5,
                    1.0,
                    Currency.USD,
                    Sector.Technology,
                    AssetClass.Equity,
                    Region.NORTH_AMERICA
                ),
                Instrument(
                    39,
                    5.0,
                    Currency.GBP,
                    Sector.Education,
                    AssetClass.Equity,
                    Region.EMEA
                ),
                Instrument(
                    32,
                    100.0,
                    Currency.Other,
                    Sector.Pharmaceutical,
                    AssetClass.Equity,
                    Region.APAC
                ),
                Instrument(
                    200,
                    30.0,
                    Currency.HKD,
                    Sector.Technology,
                    AssetClass.FixedIncome,
                    Region.APAC
                )
            ),
            Part.SECOND
        ),
        OutputPart2(
            instruments = listOf(
                Math.PI * 7 / 6,
                3.66833302,
                3.70718909,
                3.92637717,
                4.56401521,
                Math.PI * 7 / 6 + Math.PI * 2 / 3
            ),
            currency = listOf(
                Math.PI * 1 / 6,
                0.81979753,
                0.97777020,
                1.03207331,
                1.04169977,
                1.04484136
            ),
            sector = listOf(
                1.04798295,
                1.34608406,
                1.50493894,
                1.55954531,
                1.56922553,
            ),
            assetClass = listOf(
                1.57236712,
                1.87022002,
                2.09360970
            ),
            region = listOf(
                2.09675130,
                2.60522583,
                2.61485229,
                2.61799388
            )
        ),
        Part.SECOND.score
    )
)

private fun generateRandomInstrumentData(size: Int): List<Instrument> {
    return (0 until size).map {
        Instrument(
            quantity = Random.nextInt(1, 20),
            price = Random.nextDouble(1.0,100000.0),
            currency = Currency.values().toList().shuffled().first(),
            sector = Sector.values().toList().shuffled().first(),
            assetClass = AssetClass.values().toList().shuffled().first(),
            region = Region.values().toList().shuffled().first()
        )
    }
}

fun createRandomTestCase(part: Part, minInstruments: Int = 1, maxInstruments: Int = 2000): TestCase {
    val instrumentsNo = Random.nextInt(minInstruments,maxInstruments + 1)
    val instruments = generateRandomInstrumentData(instrumentsNo)
    val output = (if (part == Part.FIRST) PieChart() else ChordDiagram()).calculateCoordinates(instruments)
    return TestCase(Input(instruments, part), output, part.score)
}

private fun getDoubleReBalancingParams(): Triple<Double, Double, Int> {
    // numbers in hundredth percentage
    val doubleReBalancingInstValue = Random.nextDouble(5.5, 8.0)
    val initialOutlierValue = 5.0 / 100
    val numberOfInitialOutliers = ceil(
        1000 * ((doubleReBalancingInstValue * 2 - 10) / (doubleReBalancingInstValue - initialOutlierValue))
    )
    return Triple(doubleReBalancingInstValue, initialOutlierValue, numberOfInitialOutliers.toInt())
}

private fun generateRandomInstrumentWithGivenValue(value: Double): Instrument {
    val quantity = Random.nextInt(1, 20)
    return Instrument(
        quantity,
        price = value / quantity,
        currency = Currency.values().toList().shuffled().first(),
        sector = Sector.values().toList().shuffled().first(),
        assetClass = AssetClass.values().toList().shuffled().first(),
        region = Region.values().toList().shuffled().first()
    )
}

private fun generateInstrumentsFor2StepReBalancingP1(minInitialInstruments: Int = 1): List<Instrument> {
    val instrumentData = generateRandomInstrumentData(Random.nextInt(minInitialInstruments, 2000))
    val threshold = instrumentData.sumOf { it.price * it.quantity } * 0.05 / 100
    val instrumentsWithoutOutliers = instrumentData.filter { (it.quantity * it.price) >= threshold }

    // numbers in hundredth percentage
    val (doubleReBalancingInstValue, initialOutlierValue, numberOfInitialOutliers) = getDoubleReBalancingParams()
    val percentageOfNonOutliers = 100.0 - (doubleReBalancingInstValue / 100.0) - (numberOfInitialOutliers * initialOutlierValue / 100.0)
    val totalValue = instrumentsWithoutOutliers.sumOf { it.price * it.quantity } * 100.0 / percentageOfNonOutliers
    val outliers = (0 until numberOfInitialOutliers)
        .map { generateRandomInstrumentWithGivenValue(initialOutlierValue * totalValue / (100 * 100)) }

    return (outliers +
            instrumentsWithoutOutliers +
            generateRandomInstrumentWithGivenValue(doubleReBalancingInstValue * totalValue / (100 * 100))).shuffled()
}

fun create2StepReBalancingRandomTCP1(minInitialInstruments: Int): TestCase {
    val instruments = generateInstrumentsFor2StepReBalancingP1(minInitialInstruments)
    val output = PieChart().calculateCoordinates(instruments)
    return TestCase(Input(instruments, Part.FIRST), output, Part.FIRST.score)
}
