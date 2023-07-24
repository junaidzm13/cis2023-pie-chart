package com.csg.codeit.data

import com.csg.codeit.model.*


val testCasesForPart1 = listOf(
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
        4
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
        4
    )
)

val testCasesForPart2 = listOf(
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
        6
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
        6
    )
)

val testCases: List<TestCase> = listOf(
    testCasesForPart1,
    testCasesForPart2
).flatten()
