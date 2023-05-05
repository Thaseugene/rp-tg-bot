package com.tas.tgbot.controller;

import com.tas.tgbot.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

@RestController
public class TelegramController {

    private final BotService botService;

    @Autowired
    public TelegramController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/")
    public Mono<SendMessage> onUpdateReceived(@RequestBody Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return botService.handleMessage(update);
        } else {
            return Mono.empty();
        }

    }
}
