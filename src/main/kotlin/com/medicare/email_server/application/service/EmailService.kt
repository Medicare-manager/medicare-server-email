package com.medicare.email_server.application.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

/**
 * Component responsible for handling email-sending operations using Spring's JavaMailSender.
 *
 * This service encapsulates the logic for composing and sending simple email messages
 * (text-only emails) and acts as a reusable utility within the application for email communication.
 *
 * @property mailSender Spring's [JavaMailSender] instance used to send email messages.
 */
@Component
class EmailService(private val mailSender: JavaMailSender) {

    /**
     * Sends a simple email to the specified recipient.
     *
     * This method creates a text-based email message with the provided recipient, subject, and body.
     * The email is sent using the configured [JavaMailSender] instance.
     *
     * @param to The recipient's email address.
     * @param subject The subject of the email.
     * @param body The body of the email (plain text).
     *
     * @throws org.springframework.mail.MailException If an error occurs while sending the email.
     */
    fun sendEmail(to: String, subject: String, body: String) {
        val message = SimpleMailMessage()
        message.setTo(to)
        message.setSubject(subject)
        message.setText(body)
        mailSender.send(message)
    }
}