package com.sviryd.telegram_bot.runner;

import com.sviryd.telegram_bot.config.HostConfig;
import com.sviryd.telegram_bot.config.telegram.CityGuideBotTelegramConfig;
import com.sviryd.telegram_bot.config.telegram.TelegramBotSenderConfig;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramWebhookPostStartup {
    private final HostConfig hostConfig;
    private final CityGuideBotTelegramConfig botConfig;
    private final TelegramBotSenderConfig senderConfig;

    public TelegramWebhookPostStartup(HostConfig hostConfig, CityGuideBotTelegramConfig botConfig, TelegramBotSenderConfig senderConfig) {
        this.hostConfig = hostConfig;
        this.botConfig = botConfig;
        this.senderConfig = senderConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendRequestAfterStartupToTelegram() {
        setWebhook(botConfig.getUsername(), botConfig.getToken());
    }

    private void setWebhook(String username, String token) {
        String url = senderConfig.getSetWebhookURL(token);

        Map<String, String> params = new HashMap<>();
        params.put("url", hostConfig.getDomain() + "/webhook" + "/" + username);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, String.class);
    }
}