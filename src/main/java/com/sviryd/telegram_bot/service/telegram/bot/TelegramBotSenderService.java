package com.sviryd.telegram_bot.service.telegram.bot;

import com.sviryd.telegram_bot.config.telegram.TelegramBotSenderConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramBotSenderService {
    private final TelegramBotSenderConfig senderConfig;

    public TelegramBotSenderService(TelegramBotSenderConfig senderConfig) {
        this.senderConfig = senderConfig;
    }

    public void sendMessage(String botToken, Long chatId, String message) {
        String url = senderConfig.getSenderURL(botToken);

        Map<String, String> params = new HashMap<>();
        params.put("chat_id", chatId.toString());
        params.put("text", message);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, String.class);
    }
}
