package com.sviryd.telegram_bot.runner;

import com.sviryd.telegram_bot.config.HostConfig;
import com.sviryd.telegram_bot.config.TelegramBotConfig;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramWebhookPostStartup {
    private final HostConfig hostConfig;
    private final TelegramBotConfig botConfig;

    public TelegramWebhookPostStartup(HostConfig hostConfig, TelegramBotConfig botConfig) {
        this.hostConfig = hostConfig;
        this.botConfig = botConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendRequestAfterStartupToTelegram() {
        setWebhook();
    }

    private void setWebhook() {
        String url = "https://api.telegram.org/bot" + botConfig.getToken() + "/setWebhook";

        Map<String, String> params = new HashMap<>();
        params.put("url", hostConfig.getPublicURL() + "/webhook" + "/" + botConfig.getUsername());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, String.class);
    }
}