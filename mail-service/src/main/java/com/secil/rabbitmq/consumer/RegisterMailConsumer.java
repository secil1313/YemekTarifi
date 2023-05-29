package com.secil.rabbitmq.consumer;

import com.secil.rabbitmq.model.RegisterMailModel;
import com.secil.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterMailConsumer {
    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "${rabbitmq.registerMailQueue}")
    public void sendActivationCode(RegisterMailModel registerMailModel) {
        log.info("SendActivationCodeConsumer {}", registerMailModel.toString());
        mailSenderService.sendMail(registerMailModel);
    }
}
