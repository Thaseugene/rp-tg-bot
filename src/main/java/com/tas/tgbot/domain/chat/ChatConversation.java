package com.tas.tgbot.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatConversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("created")
    private long created;
    @JsonProperty("model")
    private String model;

    @JsonProperty("usage")
    private Usage usage;
    @JsonProperty("choices")
    private List<Choice> choices;

}
