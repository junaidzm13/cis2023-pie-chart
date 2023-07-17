package com.csg.codeit.controller

import com.csg.codeit.data.testCasesForPart1
import com.csg.codeit.data.testCasesForPart2
import com.csg.codeit.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/example")
    fun getChallengeReqAndResExample() = ResponseEntity.ok(
        mapOf(
            "${Part.FIRST} part" to RequestAndResponse(testCasesForPart1[0].input, testCasesForPart1[0].output),
            "${Part.SECOND} part" to RequestAndResponse(testCasesForPart2[0].input, testCasesForPart2[0].output)
        )
    )

    data class RequestAndResponse(
        val request: Input,
        val response: Output
    )
}