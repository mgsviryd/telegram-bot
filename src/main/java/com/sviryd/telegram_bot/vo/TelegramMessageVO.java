package com.sviryd.telegram_bot.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramMessageVO {
    @JsonProperty("message_id")
    private Long id;
    private TelegramFromVO from;
    private TelegramChatVO chat;
    private Long date;
    private String text;
}
