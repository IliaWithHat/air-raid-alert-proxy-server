package org.ilia.airraidalertproxyserver.exception.handler

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(val message: String, val status: Int, val timestamp: LocalDateTime) {

    constructor(message: String, status: HttpStatus) : this(
        message,
        status.value(),
        LocalDateTime.now()
    )
}