package com.secil.rabbitmq.producer;

import com.secil.rabbitmq.model.RecipeMailModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeMailProducer {
    @Value("${rabbitmq.exchange-recipe}")
    private String exchange;
    @Value("${rabbitmq.registerKey}")
    private String registerKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendFavoriteFoodwithSameCategoriesFood(RecipeMailModel recipeMailModel){
        log.info("SendFavoriteFoodwithSameCategoriesFood {}", recipeMailModel.toString());
        rabbitTemplate.convertAndSend(exchange,registerKey,recipeMailModel);


    }
}
