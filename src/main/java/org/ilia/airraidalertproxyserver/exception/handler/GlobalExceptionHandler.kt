package org.ilia.airraidalertproxyserver.exception.handler

import org.ilia.airraidalertproxyserver.exception.AlertFetchException
import org.ilia.airraidalertproxyserver.exception.AlertNotFoundException
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AlertNotFoundException::class)
    fun handleAlertNotFoundException(ex: AlertNotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.message.toString(), NOT_FOUND)
        return ResponseEntity.status(errorResponse.status).body(errorResponse)
    }

    @ExceptionHandler(AlertFetchException::class)
    fun handleAlertFetchException(ex: AlertFetchException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.message.toString(), INTERNAL_SERVER_ERROR)
        return ResponseEntity.status(errorResponse.status).body(errorResponse)
    }
}