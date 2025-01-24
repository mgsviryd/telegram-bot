package com.sviryd.telegram_bot.controller.rest;

import com.sviryd.telegram_bot.config.telegram.CityGuideBotTelegramConfig;
import com.sviryd.telegram_bot.service.telegram.bot.CityGuideTelegramBotService;
import com.sviryd.telegram_bot.vo.TelegramUpdateVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramBotRestController {
    private final CityGuideBotTelegramConfig botConfig;
    private final CityGuideTelegramBotService cityGuideTelegramBotService;

    public TelegramBotRestController(CityGuideBotTelegramConfig botConfig, CityGuideTelegramBotService cityGuideTelegramBotService) {
        this.botConfig = botConfig;
        this.cityGuideTelegramBotService = cityGuideTelegramBotService;
    }

    @PostMapping("/webhook/mgsviryd_bot")
    public ResponseEntity<String> mgsvirydBot(
            @RequestBody TelegramUpdateVO update
    ) {
        cityGuideTelegramBotService.processUpdate(botConfig.getToken(), update);
        return ResponseEntity.ok("Update received");
    }
}