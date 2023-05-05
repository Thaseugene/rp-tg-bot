package com.tas.tgbot.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Choice {

    @JsonProperty("message")
    private Map<String, String> message;
    @JsonProperty("finish_reason")
    private String finishReason;
    @JsonProperty("index")
    private long index;

}
