package com.tas.tgbot.service.impl;

import com.tas.tgbot.service.BotService;
import com.tas.tgbot.service.ChatClientService;
import com.tas.tgbot.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

@Service
public class BotServiceImpl implements BotService {

    public static final String CONTENT_KEY = "content";
    private final ChatClientService chatClientService;
    private static final Logger LOGGER = LogManager.getRootLogger();
    @Autowired
    public BotServiceImpl(ChatClientService chatClientService) {
        this.chatClientService = chatClientService;
    }

    @Override
    public Mono<SendMessage> handleMessage(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        LOGGER.info("user: {}", message.getText());
        return chatClientService.fetchDataFromChat(message.getText())
                .map(x -> new SendMessage(chatId, x.getChoices().get(0).getMessage().get(CONTENT_KEY)))
                .onErrorResume(ServiceException.class, se -> {
                    LOGGER.error("An error occurred while processing the message.", se);
                    return Mono.just(new SendMessage(chatId, "An error occurred while processing the message."));
                });
    }
}
