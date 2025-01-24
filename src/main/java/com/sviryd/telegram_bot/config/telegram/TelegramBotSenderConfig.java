package com.sviryd.telegram_bot.config.telegram;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot.sender")
public class TelegramBotSenderConfig {
    private String host;
    private String sendMessagePath;
    private String setWebhookPath;

    public String getSenderURL(String botToken) {
        return getHost() + botToken + getSendMessagePath();
    }

    public String getSetWebhookURL(String botToken) {
        return getHost() + botToken + getSetWebhookPath();
    }
}
