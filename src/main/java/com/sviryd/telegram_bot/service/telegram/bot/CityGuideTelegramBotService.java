package com.sviryd.telegram_bot.service.telegram.bot;

import com.sviryd.telegram_bot.vo.TelegramMessageVO;
import com.sviryd.telegram_bot.vo.TelegramUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CityGuideTelegramBotService {
    private final TelegramBotSenderService senderService;
    private final CityGuideService cityGuideService;

    @Autowired
    public CityGuideTelegramBotService(TelegramBotSenderService senderService, CityGuideService cityGuideService) {
        this.senderService = senderService;
        this.cityGuideService = cityGuideService;
    }

    public void processUpdate(String botToken, TelegramUpdateVO update) {
        TelegramMessageVO message = update.getMessage();
        if (message.getChat() != null) {
            Long chatId = message.getChat().getId();
            String text = message.getText();
            Locale locale = new Locale(message.getFrom().getLanguageCode());
            String information = cityGuideService.getCityInfo(text, locale);
            senderService.sendMessage(botToken, chatId, information);
        }
    }
}