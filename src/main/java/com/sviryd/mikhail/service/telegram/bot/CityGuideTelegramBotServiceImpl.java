package com.sviryd.mikhail.service.telegram.bot;

import com.sviryd.mikhail.config.CityGuideTelegramBotConfig;
import com.sviryd.mikhail.entity.City;
import com.sviryd.mikhail.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class CityGuideTelegramBotService extends TelegramLongPollingBot {
    private static final String CITY_NOT_FOUND_MSG = "The city {0} is not found.";
    private final CityGuideTelegramBotConfig config;
    private final CityService cityService;

    @Autowired
    public CityGuideTelegramBotService(CityGuideTelegramBotConfig config, CityService cityService) {
        this.config = config;
        this.cityService = cityService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        Message incomeMsg = update.getMessage();
        if (incomeMsg.hasText()) {
            String messageText = incomeMsg.getText();
            String information = getCityInformation(messageText);

            SendMessage message = new SendMessage()
                    .setChatId(incomeMsg.getChatId())
                    .setText(information);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getCityInformation(String messageText) {
        final Optional<City> city = cityService.findByName(messageText);
        return city
                .map(City::getInformation)
                .orElseGet(() -> MessageFormat.format(CITY_NOT_FOUND_MSG, messageText));
    }

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}