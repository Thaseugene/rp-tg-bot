package com.tas.tgbot.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Getter
@PropertySource(value = "classpath:bot_config.properties")
@Component
public class BotConfig {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.webhookUrl}")
    private String webhookUrl;


}
