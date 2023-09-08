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
            "Part I" to (0 until 2).map {
                RequestAndResponse(hardCodedTestCasesPart1[it].input, hardCodedTestCasesPart1[it].output)
            },
            "Part II" to (0 until 2).map {
                RequestAndResponse(hardCodedTestCasesPart2[it].input, hardCodedTestCasesPart2[it].output)
            }
        )
    )

    data class RequestAndResponse(
        val request: Input,
        val response: Output
    )
}