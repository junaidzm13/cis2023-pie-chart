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
            Part.FIRST
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
    )
)

val testCases: List<TestCase> = listOf(
    testCasesForPart1,
    testCasesForPart2
).flatten()
