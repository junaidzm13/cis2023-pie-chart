package com.csg.codeit.data

import com.csg.codeit.model.TestCase
import org.springframework.stereotype.Component

@Component
class TestCasesProvider {
    fun getTestCases(): List<TestCase> = testCases
}