package com.csg.codeit.model

data class Input(
    val data: List<Instrument>,
    val part: Part
)

enum class Part(val score: Int) {
    FIRST(4),
    SECOND(6);
}

data class Instrument(
    val quantity: Int,
    val price: Double,
    val currency: Currency,
    val sector: Sector,
    val assetClass: AssetClass,
    val region: Region
)

enum class Currency {
    USD,
    GBP,
    EUR,
    HKD,
    SGD,
    JPY,
    RMB,
    Other
}

enum class Sector {
    Technology,
    Finance,
    Insurance,
    Education,
    ECommerce,
    Pharmaceutical,
    Sports,
    Gaming,
    Other
}

enum class AssetClass {
    Equity,
    FixedIncome,
    Etfs,
    MutualFunds,
    RealEstate,
    Derivatives,
}

enum class Region {
    APAC,
    NORTH_AMERICA,
    EMEA,
    SWITZERLAND,
    Other,
}