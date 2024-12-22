package com.medicare.email_server.application.service

import com.medicare.email_server.domain.model.EmailRequest
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer(private val emailService: EmailService) {

    @RabbitListener(queues = ["login-email-queue"])
    fun handleEmailMessage(emailMessage: EmailRequest) {
        emailService.sendEmail(emailMessage.to, emailMessage.subject, emailMessage.body)
    }
}