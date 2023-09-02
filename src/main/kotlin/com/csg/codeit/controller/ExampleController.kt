package com.csg.codeit.controller

import com.csg.codeit.data.hardCodedTestCasesPart1
import com.csg.codeit.data.hardCodedTestCasesPart2
import com.csg.codeit.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/example")
    fun getChallengeReqAndResExample() = ResponseEntity.ok(
        mapOf(
            "${Part.FIRST} part" to RequestAndResponse(hardCodedTestCasesPart1[0].input, hardCodedTestCasesPart1[0].output),
            "${Part.SECOND} part" to RequestAndResponse(hardCodedTestCasesPart2[0].input, hardCodedTestCasesPart2[0].output)
        )
    )

    data class RequestAndResponse(
        val request: Input,
        val response: Output
    )
}