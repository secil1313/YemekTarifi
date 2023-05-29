package com.secil.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
//forgotpassword
@Value("${rabbitmq.registerMailQueue}")
private String registerMailQueue;

    @Bean
    Queue registerMailQueue(){
        return new Queue(registerMailQueue);
    }

    //favorite food
    @Value("${rabbitmq.queueRegisterRecipe}")
    private String registerMailRecipeQueue;
    @Bean
    Queue recipeMailQueue(){
        return new Queue(registerMailRecipeQueue);
    }

}
