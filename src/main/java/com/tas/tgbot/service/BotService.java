package com.tas.tgbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

public interface BotService {

    Mono<SendMessage> handleMessage(Update update);

}
