package com.csg.codeit.data

import com.csg.codeit.model.*

val testCases: List<TestCase> = listOf(
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
            Math.PI * 2 * 3 / 5,
            Math.PI * 2 * 4 / 5,
        )),
        4
    )
)