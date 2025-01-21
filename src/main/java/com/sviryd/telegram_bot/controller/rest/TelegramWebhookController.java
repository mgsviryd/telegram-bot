package com.sviryd.telegram_bot.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sviryd.telegram_bot.service.telegram.bot.CityGuideTelegramBotService;
import com.sviryd.telegram_bot.vo.TelegramUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramWebhookController {
    @Autowired
    private CityGuideTelegramBotService cityGuideTelegramBotService;
    @Value("${telegram.bot.token}")
    private String botToken;

    @PostMapping("/webhook/mgsviryd_bot")
    public ResponseEntity<String> mgsvirydBot(
            @RequestBody String update
    ) throws JsonProcessingException {
        TelegramUpdateVO messageVO = new ObjectMapper().readValue(update, TelegramUpdateVO.class);
        cityGuideTelegramBotService.processUpdate(botToken, messageVO);
        return ResponseEntity.ok("Update received");
    }
}