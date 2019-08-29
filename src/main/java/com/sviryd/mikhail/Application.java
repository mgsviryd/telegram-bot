package com.sviryd.mikhail;

import com.sviryd.mikhail.service.dao.CityService;
import com.sviryd.mikhail.service.telegram.bot.TelegramBotCityGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private TelegramBotCityGuideService service;
    static {
        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String username = "cityGuideForOnTravelSolutionsBot";
        String token = "973312723:AAFKaxG0JMf2T0jAZboG0b72fVJnqmgacy0";
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(service);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}