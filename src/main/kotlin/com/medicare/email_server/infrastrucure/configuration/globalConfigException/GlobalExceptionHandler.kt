package com.medicare.email_server.infrastrucure.configuration.globalConfigException

import com.medicare.email_server.domain.exception.InvalidEmailMessageException
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Global exception handler for RabbitMQ message processing.
 */
class GlobalExceptionHandler {

    /**
     * Handles InvalidEmailMessageException and returns an appropriate response.
     *
     * @param ex The exception instance.
     * @return A descriptive error message.
     */
    @ExceptionHandler(InvalidEmailMessageException::class)
    fun handleInvalidEmailMessageException(ex: InvalidEmailMessageException): String {
        return "Error processing email message: ${ex.message}"
    }

    /**
     * Handles any unexpected errors during RabbitMQ message processing.
     */
    @ExceptionHandler(ListenerExecutionFailedException::class)
    fun handleListenerExecutionFailedException(ex: ListenerExecutionFailedException): String {
        return "Error in RabbitMQ listener: ${ex.message}"
    }
}