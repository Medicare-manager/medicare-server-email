package com.medicare.email_server.domain.model

data class EmailMessage(
    val to: String,
    val subject: String,
    val body: String
)
