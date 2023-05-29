package com.secil.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-recipe}")
    String exchange;
    @Bean
    DirectExchange exchangeRecipe(){
        return new DirectExchange(exchange);
    }
    @Value("${rabbitmq.queueRegisterRecipe}")
    private String registerMailQueue;
    @Value("${rabbitmq.registerKey}")
    private String registerMailBindingKey;
    @Bean
    Queue recipeMailQueue(){
        return new Queue(registerMailQueue);
    }
    @Bean
    public Binding bindingRegisterMail(final Queue registerMailQueue, final DirectExchange exchangeRecipe){
        return BindingBuilder.bind(registerMailQueue).to(exchangeRecipe).with(registerMailBindingKey);
    }
}
