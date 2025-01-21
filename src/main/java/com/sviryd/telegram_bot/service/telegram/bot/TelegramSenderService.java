package com.sviryd.telegram_bot.service.telegram.bot;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramSenderService {
    public void sendMessage(String botToken, Long chatId, String message) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        Map<String, String> params = new HashMap<>();
        params.put("chat_id", chatId.toString());
        params.put("text", message);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, String.class);
    }
}
