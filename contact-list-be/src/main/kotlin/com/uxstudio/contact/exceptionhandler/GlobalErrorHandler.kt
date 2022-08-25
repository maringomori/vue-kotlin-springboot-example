package com.uxstudio.contact.exceptionhandler

import com.uxstudio.contact.exception.ContactNotFoundException
import com.uxstudio.contact.exception.FileNotFoundException
import org.springframework.beans.BeanInstantiationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.NullPointerException


@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler
    fun handleFileNotFoundException(ex: FileNotFoundException): ResponseEntity<Any> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleContactNotFoundException(ex: ContactNotFoundException): ResponseEntity<Any> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleBeanInstantiationException(ex: BeanInstantiationException) : ResponseEntity<Any> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errors = ex.bindingResult.allErrors
            .map { error -> error.defaultMessage!! }
            .sorted()


        val errorMessage = ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            errors.joinToString(", ") { it }
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.bindingResult.allErrors
            .map { error -> error.defaultMessage!! }
            .sorted()


        val errorMessage = ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            errors.joinToString(", ") { it }
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(java.lang.Exception::class)
    fun handleAllExceptions(ex: java.lang.Exception, request: WebRequest): ResponseEntity<Any> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}