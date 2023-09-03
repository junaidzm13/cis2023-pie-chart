# Pie chart (portfolio visualization)

### Endpoint

Your application must expose <b>/pie-chart</b> endpoint which will accept POST requests in the same format as specified
in the examples below.

Input will be provided with MIME type of <b>application.json</b>. Output must be given with the same MIME type.

### Description

Over the past few months, your friend, Joe, has bought several financial instruments. As his investments grew so 
did the difficulty to track his portfolio's risk. As a result, he decides to build several visualizations to track his
portfolio's proportions and associated risks. Since, he is a really close friend of yours, you agree to help.

You are given a portfolio made up of **N** instruments, each instrument having the following attributes:
  - quantity
  - price *(in USD)*
  - currency
  - sector
  - assetClass
  - region

<hr style="height:0.25em; background-color:#e1e4e8">

### First visualization

For the first visualization, your friend would like you to build a pie chart to help visualize the amount invested in
each instrument.

Your task is to calculate `N + 1` angles (in `radians`) representing the boundaries of the slices/arcs of the pie chart, where each
slice/arc represents the relative investment allocation for an instrument. The slices/arcs should be arranged in descending order
of their sizes (instrument's proportion) in clockwise direction.
You should return a list containing `N + 1` angles where `1 <= N <= 2000`.

For example, if Portfolio A is made up of 3 instruments S1, S2 and S3 with the following proportions 20%, 20% and 60% respectively,
then if we draw a pie chart sorted by the invested value of instruments we should get:

![Part1-visualization.png](./Part1-visualization.png)

Then the program should return the following list:

```
[
    0.0, // start of pie chart
    3.76991118, // boundary of slices of S3, S2
    5.02654825, // boundary of slices of S2, S1
    6.28318531  // end of pie chart
]
```
<hr style="height:0.0625em; background-color:#e1e4e8">

#### Constraint
Since, it will be hard to visualize investments with very small proportions, your friend adds the following constraint:
*if any instrument's invested proportion makes up less than **0.05%** (or 0.00314159 radians), its slice/arc must be
assigned the corresponding minimum value in radians and the angles of other slices/arcs must be adjusted proportionally.*

<hr style="height:0.0625em; background-color:#e1e4e8">

#### Example 1

**Input**
```json
{
  "instruments": [
    {
      "quantity": 4,
      "price": 5.0,
      "currency": "HKD",
      "sector": "ECommerce",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 5,
      "price": 4.0,
      "currency": "JPY",
      "sector": "Finance",
      "assetClass": "FixedIncome",
      "region": "APAC"
    },
    {
      "quantity": 10,
      "price": 6.0,
      "currency": "EUR",
      "sector": "Education",
      "assetClass": "Derivatives",
      "region": "EMEA"
    }
  ],
  "part": "FIRST"
}
```

**Output**
```json
{
  "instruments": [
    0.0,
    3.76991118,
    5.02654825,
    6.28318531   
  ]
}
```

#### Example 2

**Input**

```json
{
  "instruments": [
    {
      "quantity": 110,
      "price": 10.0,
      "currency": "SGD",
      "sector": "ECommerce",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 5,
      "price": 1.0,
      "currency": "USD",
      "sector": "Technology",
      "assetClass": "Equity",
      "region": "NORTH_AMERICA"
    },
    {
      "quantity": 39,
      "price": 5.0,
      "currency": "GBP",
      "sector": "Education",
      "assetClass": "Equity",
      "region": "EMEA"
    },
    {
      "quantity": 32,
      "price": 100.0,
      "currency": "Other",
      "sector": "Pharmaceutical",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 200,
      "price": 30.0,
      "currency": "HKD",
      "sector": "Technology",
      "assetClass": "FixedIncome",
      "region": "APAC"
    }
  ],
  "part": "FIRST" 
}
```

**Output**
```json
{
  "instruments": [
    0.0,
    3.59030608,
    5.50513599,
    6.16335877,
    6.28004372,
    6.28318531
  ]
}
```

<hr style="height:0.25em; background-color:#e1e4e8">

### Second visualization

For the second visualization, your friend's proposal is to build a split chord diagram to help visualize not only the
relative amount invested in each instrument but also the risk exposures under each of the following categories:
  1. currency
  2. sector
  3. assetClass 
  4. region

Your task again is to calculate angles (in radians).

#### Split Chord Diagram (only for reference)
![Part2-visualization.png](./Part2-visualization.png)

For the above diagram, only consider the outer skeleton (ignoring the connectors/ribbons). On the left-hand side,
arks are ordered by size (ascending, clockwise) and each ark represents an instrument in the portfolio with the size of arc proportional to
the value invested in the instrument.

On the right, we have four main arcs of same size representing the value invested in each category. Each main ark is
then further divided into smaller arcs *(ordered by size, clockwise descending)* representing the proportion invested in each type under a given category
(if category is `currency`, then `type` would be `USD`, `HKD`, `JPY`, etc.). 

 - On the right the arks should be in the following order (clockwise): `currency`, `sector`, `assetClass`, `region`.
 - Space between main arks on the right is `0.00314159 radians`
 - Tops of the left and right side should be symmetric and `PI / 3 radians` apart.
 - Bottoms of the left and right side should be symmetric and `PI / 3 radians` apart.

As before you have to calculate `N + 1` angles representing relative investment allocations for each instrument in the
portfolio. In addition to that, given `c`, `s`, `a`, `r` integers representing different types of currencies, sectors, asset classes and regions respectively,
you also need to calculate `c + 1`, `s + 1`, `a + 1`, `r + 1` angles making up the four main arcs representing each risk category.
Where `1 <= N <= 666`, `1 <= c <= 8`, `1 <= s <= 9`, `1 <= a <= 6`, `1 <= r <= 5`.

<hr style="height:0.0625em; background-color:#e1e4e8">

#### Constraint
Again, since it will be hard to visualize investments with very small proportions, please consider the following constraint
in your calculations: if an arc has an angle smaller than **0.00314159**, it must be re-assigned the corresponding 
minimum value in radians and the angles of other arcs must be adjusted proportionally.

<hr style="height:0.0625em; background-color:#e1e4e8">

#### Example 1

**Input**
```json
{
  "instruments": [
    {
      "quantity": 4,
      "price": 5.0,
      "currency": "HKD",
      "sector": "ECommerce",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 5,
      "price": 4.0,
      "currency": "JPY",
      "sector": "Finance",
      "assetClass": "FixedIncome",
      "region": "APAC"
    },
    {
      "quantity": 10,
      "price": 6.0,
      "currency": "EUR",
      "sector": "Education",
      "assetClass": "Derivatives",
      "region": "EMEA"
    }
  ],
  "part": "SECOND"
}
```

**Output**
```json
{
  "instruments": [
    3.66519143,
    4.08407045,
    4.50294947,
    5.75958653
  ],
  "currency": [
    0.52359878,
    0.83634432,
    0.94059284,
    1.04484136
  ],
  "sector": [
    1.04798295,
    1.36072850,
    1.46497701,
    1.56922553
  ],
  "assetClass": [
    1.57236712,
    1.88511267,
    1.98936119,
    2.09360970
  ],
  "region": [
    2.09675130,
    2.40949684,
    2.61799388
  ]
}
```

#### Example 2

**Input**

```json
{
  "instruments": [
    {
      "quantity": 110,
      "price": 10.0,
      "currency": "SGD",
      "sector": "ECommerce",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 5,
      "price": 1.0,
      "currency": "USD",
      "sector": "Technology",
      "assetClass": "Equity",
      "region": "NORTH_AMERICA"
    },
    {
      "quantity": 39,
      "price": 5.0,
      "currency": "GBP",
      "sector": "Education",
      "assetClass": "Equity",
      "region": "EMEA"
    },
    {
      "quantity": 32,
      "price": 100.0,
      "currency": "Other",
      "sector": "Pharmaceutical",
      "assetClass": "Equity",
      "region": "APAC"
    },
    {
      "quantity": 200,
      "price": 30.0,
      "currency": "HKD",
      "sector": "Technology",
      "assetClass": "FixedIncome",
      "region": "APAC"
    }
  ],
  "part": "SECOND" 
}
```

**Output**
```json
{
  "instruments": [
    3.66519143,
    3.66833302,
    3.70718909,
    3.92637717,
    4.56401521,
    5.75958653
  ],
  "currency": [
    0.52359878,
    0.81979753,
    0.97777020,
    1.03207331,
    1.04169977,
    1.04484136
  ],
  "sector": [
    1.04798295,
    1.34608406,
    1.50493894,
    1.55954531,
    1.56922553
  ],
  "assetClass": [
    1.57236712,
    1.87022002,
    2.09360970
  ],
  "region": [
    2.09675130,
    2.60522583,
    2.61485229,
    2.61799388
  ]
}
```
<hr style="height:0.25em; background-color:#e1e4e8">