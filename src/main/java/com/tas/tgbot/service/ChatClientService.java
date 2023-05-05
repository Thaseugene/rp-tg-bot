package com.tas.tgbot.service;

import com.tas.tgbot.domain.chat.ChatConversation;
import reactor.core.publisher.Mono;

public interface ChatClientService {

    Mono<ChatConversation> fetchDataFromChat(String content);

}
