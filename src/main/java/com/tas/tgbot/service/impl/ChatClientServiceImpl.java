package com.tas.tgbot.service.impl;

import com.tas.tgbot.domain.bot.input.Message;
import com.tas.tgbot.domain.bot.input.RequestChatMessage;
import com.tas.tgbot.domain.chat.ChatConversation;
import com.tas.tgbot.service.ChatClientService;
import com.tas.tgbot.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@PropertySource(value = "classpath:chat_config.properties")
public class ChatClientServiceImpl implements ChatClientService {
    @Value("${gpt.user.role}")
    private String role;
    @Value("${gpt.temperature}")
    private double temperature;
    @Value("${gpt.model}")
    private String model;

    private final WebClient webClient;
    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    public ChatClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ChatConversation> fetchDataFromChat(String content) {
        RequestChatMessage requestChatMessage = new RequestChatMessage(
                model,
                List.of(new Message(role, content)),
                temperature);

        return webClient.post()
                .bodyValue(requestChatMessage)
                .retrieve()
                .bodyToMono(ChatConversation.class)
                .onErrorResume(Exception.class, e -> {
                    LOGGER.error("An error occurred while fetching data from chat.", e);
                    return Mono.error(new ServiceException("An error occurred while fetching data from chat.", e));
                });
    }

}
