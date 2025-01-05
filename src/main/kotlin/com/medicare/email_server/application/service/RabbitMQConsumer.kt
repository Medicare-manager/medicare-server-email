package com.medicare.email_server.application.service

import com.medicare.email_server.domain.exception.InvalidEmailMessageException
import com.medicare.email_server.domain.model.EmailMessage
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

/**
 * Service responsible for consuming messages from the RabbitMQ queue and processing them as email send requests.
 *
 * @property emailService Service used to send emails based on the processed messages.
 */
@Service
class RabbitMQConsumer(private val emailService: EmailService) {

    /**
     * Consumes messages from the "login-email-queue" RabbitMQ queue and processes the received data
     * to send an email using the email service.
     *
     * This method is automatically invoked whenever a new message arrives in the configured queue.
     *
     * @param emailMessage Object containing the data received from the queue. The Spring framework
     *                     automatically deserializes the JSON message into the [EmailMessage] class.
     *
     * @throws org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException
     *         Thrown if an error occurs while consuming the message or if deserialization fails.
     */
    @RabbitListener(queues = ["login-email-queue"])
    fun handleEmailMessage(emailMessage: EmailMessage) {
        if (emailMessage.to.isBlank() || emailMessage.subject.isBlank() || emailMessage.body.isBlank()) {
            throw InvalidEmailMessageException("All fields (to, subject, body) must be filled. Received: $emailMessage")
        }

        emailService.sendEmail(emailMessage.to, emailMessage.subject, emailMessage.body)
    }
}