package com.medicare.email_server.adapters.`in`.controller

import com.medicare.email_server.application.service.EmailService
import com.medicare.email_server.domain.model.EmailMessage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for managing email sending.
 *
 * This class provides an endpoint for sending emails using the `EmailService`.
 * Clients can send requests to send an email by specifying the recipient, subject, and body of the message.
 *
 * Available endpoints:
 * - POST /api/emails/send: Sends an email based on the provided request data.
 *
 * @param emailService The service responsible for handling email sending operations.
 */
@RestController
@RequestMapping("/api/emails")
class EmailController(private val emailService: EmailService) {

    /**
     * Endpoint to send emails.
     *
     * This method handles POST requests with the details of the email provided in the request body.
     * If the email is sent successfully, it returns a success message.
     * If an error occurs during the sending process, it returns an error message with the reason.
     *
     * @param request An object containing the email details to be sent, including:
     * - `to`: The recipient's email address.
     * - `subject`: The subject of the email.
     * - `body`: The body of the email.
     *
     * @return ResponseEntity containing:
     * - Status 200 (OK) and a success message if the email is sent successfully.
     * - Status 400 (Bad Request) and an error message if there is a failure during the sending process.
     */
    @PostMapping("/send")
    fun sendEmail(@RequestBody request: EmailMessage): ResponseEntity<String> {
        return try {
            emailService.sendEmail(request.to, request.subject, request.body)
            ResponseEntity.ok("E-mail enviado com sucesso para: ${request.to}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Erro ao enviar e-mail: ${ex.message}")
        }
    }
}