package com.sviryd.telegram_bot.service.telegram.bot;

import com.sviryd.telegram_bot.entity.City;
import com.sviryd.telegram_bot.service.CityService;
import com.sviryd.telegram_bot.vo.TelegramMessageVO;
import com.sviryd.telegram_bot.vo.TelegramUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class CityGuideTelegramBotService {
    private static final String CITY_NOT_FOUND_MSG = "The city {0} is not found.";
    private final CityService cityService;

    private final TelegramSenderService senderService;

    @Autowired
    public CityGuideTelegramBotService(CityService cityService, TelegramSenderService senderService) {
        this.cityService = cityService;
        this.senderService = senderService;
    }

    public void processUpdate(String botToken, TelegramUpdateVO update) {
        TelegramMessageVO message = update.getMessage();
        if (message.getChat() != null) {
            Long chatId = message.getChat().getId();
            String text = message.getText();
            String information = getCityInformation(text);
            senderService.sendMessage(botToken, chatId, information);
        }
    }

    private String getCityInformation(String messageText) {
        final Optional<City> city = cityService.findByName(messageText);
        return city
                .map(City::getInformation)
                .orElseGet(() -> MessageFormat.format(CITY_NOT_FOUND_MSG, messageText));
    }
}