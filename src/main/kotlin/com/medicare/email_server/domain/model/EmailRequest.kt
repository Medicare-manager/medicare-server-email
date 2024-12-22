package com.medicare.email_server.domain.model

data class EmailRequest (
    val to: String,
    val subject: String,
    val body: String
)