package com.medicare.email_server.adapters.`in`.controller

import com.medicare.email_server.application.service.EmailService
import com.medicare.email_server.domain.model.EmailRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/emails")
class EmailController(private val emailService: EmailService) {

    @PostMapping("/send")
    fun sendEmail(@RequestBody request: EmailRequest): ResponseEntity<String> {
        println("Olá, Kotlin! "+request)
        return try {
            emailService.sendEmail(request.to, request.subject, request.message)
            ResponseEntity.ok("E-mail enviado com sucesso para: ${request.to}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Erro ao enviar e-mail: ${ex.message}")
        }
    }
}