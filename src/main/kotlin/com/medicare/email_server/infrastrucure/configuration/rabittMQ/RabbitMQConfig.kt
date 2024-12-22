package com.medicare.email_server.infrastrucure.configuration.rabittMQ

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun emailQueue(): Queue {
        return Queue("login-email-queue", true)
    }
}
