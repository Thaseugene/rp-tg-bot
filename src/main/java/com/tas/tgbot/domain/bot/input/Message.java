package com.tas.tgbot.domain.bot.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}
