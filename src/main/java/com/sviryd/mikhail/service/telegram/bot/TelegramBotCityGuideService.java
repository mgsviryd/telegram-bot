package com.sviryd.mikhail.service.telegram.bot;

import com.sviryd.mikhail.dao.entity.City;
import com.sviryd.mikhail.service.dao.CityService;
import com.sviryd.mikhail.telegram.bot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBotCityGuideService extends TelegramBot {
    private final CityService cityService;

    @Autowired
    public TelegramBotCityGuideService(@Value("${bot.username}") String botUsername, @Value("${bot.token}") String botToken, CityService cityService) {
        super(botUsername, botToken);
        this.cityService = cityService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            final City city = cityService.findByName(message_text);
            String information;
            if (city != null) {
                information = city.getInformation();
            } else {
                information = "The city '" + message_text + "' is not found.";
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(information);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
