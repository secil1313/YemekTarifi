package com.secil.rabbitmq.consumer;
import com.secil.rabbitmq.model.RecipeMailModel;
import com.secil.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeMailConsumer {
    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "${rabbitmq.queueRegisterRecipe}")
    public void sendFavoriteFood(RecipeMailModel recipeMailModel){
        log.info("SendFavoriteFoodConsumer {}", recipeMailModel.toString());
        mailSenderService.sendRecipeMail(recipeMailModel);
    }
}
