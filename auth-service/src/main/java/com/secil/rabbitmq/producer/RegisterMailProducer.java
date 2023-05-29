package com.secil.rabbitmq.producer;

import com.secil.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterMailProducer {

    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(RegisterMailModel registerMailModel){
        log.info("SendActivationCode {}", registerMailModel.toString());
        rabbitTemplate.convertAndSend(directExchange,registerMailBindingKey, registerMailModel);
    }
}
