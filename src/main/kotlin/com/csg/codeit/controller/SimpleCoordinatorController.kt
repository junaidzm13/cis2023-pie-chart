package com.csg.codeit.controller

import com.csg.codeit.model.EvaluationResultRequest
import com.csg.codeit.model.Input
import com.csg.codeit.model.OutputPart1
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleCoordinatorController {
    private val logger: Logger = LoggerFactory.getLogger(SimpleCoordinatorController::class.java)

    @PostMapping(value = ["/coordinator"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun evaluate(@RequestBody evaluationResultRequest: EvaluationResultRequest): ResponseEntity<Void> =
        ResponseEntity<Void>(HttpStatus.ACCEPTED).also {
            with(evaluationResultRequest) { logger.info("Score for $runId = $score; Message:\n$message") }
        }

    @PostMapping(value = ["/mock-user"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun evaluate(@RequestBody input: Input): ResponseEntity<OutputPart1> {
        logger.info("Recieved: $input")
        return ResponseEntity.ok(
            OutputPart1(
                listOf(
                    3.769911,
                    5.026548
                )
            )
        )
    }
}